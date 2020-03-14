package storm.myself.test.scheduler;



import java.util.HashMap;
import java.util.Map;

import org.apache.storm.Config;
import org.apache.storm.Constants;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;


public class StormTopologyTimer2 {

    public static class MySpout extends BaseRichSpout{
        private Map conf;
        private TopologyContext context;
        private SpoutOutputCollector collector;
        @Override
        public void open(Map conf, TopologyContext context,
                         SpoutOutputCollector collector) {
            this.conf = conf;
            this.collector = collector;
            this.context = context;
        }

        int num = 0;
        @Override
        public void nextTuple() {
            num++;
            System.out.println("spout:"+num);
            this.collector.emit(new Values(num));
            Utils.sleep(1000);
        }

        @Override
        public void declareOutputFields(OutputFieldsDeclarer declarer) {
            declarer.declare(new Fields("num"));
        }

    }



    public static class MyBolt extends BaseRichBolt{

        private Map stormConf;
        private TopologyContext context;
        private OutputCollector collector;
        @Override
        public void prepare(Map stormConf, TopologyContext context,
                            OutputCollector collector) {
            this.stormConf = stormConf;
            this.context = context;
            this.collector = collector;
        }

        int sum = 0;
        @Override
        public void execute(Tuple input) {
            if(input.getSourceComponent().equals(Constants.SYSTEM_COMPONENT_ID)){
                System.out.println("定时时间到了");
            }else{
                Integer num = input.getIntegerByField("num");
                sum += num;
                System.out.println("sum="+sum);
            }
        }

        @Override
        public void declareOutputFields(OutputFieldsDeclarer declarer) {

        }

        @Override
        public Map<String, Object> getComponentConfiguration() {
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put(Config.TOPOLOGY_TICK_TUPLE_FREQ_SECS, 10);//给当前bolt设置定时任务
            return hashMap;
        }


    }



    public static void main(String[] args) {
        TopologyBuilder topologyBuilder = new TopologyBuilder();
        String spout_id = MySpout.class.getSimpleName();
        String bolt_id = MyBolt.class.getSimpleName();

        topologyBuilder.setSpout(spout_id, new MySpout());
        topologyBuilder.setBolt(bolt_id, new MyBolt()).shuffleGrouping(spout_id);


        Config config = new Config();
        String topology_name = StormTopologyTimer2.class.getSimpleName();
        if(args.length==0){
            //在本地运行
            LocalCluster localCluster = new LocalCluster();
            localCluster.submitTopology(topology_name, config, topologyBuilder.createTopology());
        }else{
            //在集群运行
            try {
                StormSubmitter.submitTopology(topology_name, config, topologyBuilder.createTopology());
            } catch (AlreadyAliveException e) {
                e.printStackTrace();
            } catch (InvalidTopologyException e) {
                e.printStackTrace();
            } catch (AuthorizationException e) {
                e.printStackTrace();
            }
        }

    }

}
