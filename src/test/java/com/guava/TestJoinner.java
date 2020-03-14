package com.guava;

import com.google.common.base.Joiner;
import com.google.common.io.Files;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

/**
 * joinner  的使用
 *
 */
public class TestJoinner {

    private final List<String> stringList = Arrays.asList("guava", "google", "java", "kafka");

    private final List<String> stringListWithNullValue = Arrays.asList("guava", "google", "java", null);

    private final String targetName = "E:\\test\\a.txt";
    @Test
    public void joinOnTest(){
        String join = Joiner.on("#").join(stringList);
        assertThat(join,equalTo("guava#google#java#kafka"));
    }

    @Test
    public void joinOnTestWithNullValueSkip(){
        String join1 = Joiner.on("#").skipNulls().join(stringListWithNullValue);
        assertThat(join1,equalTo("guava#google#java"));
    }

    @Test
    public void joinOnTestWithNullValueUsedefault(){
        String join1 = Joiner.on("#").useForNull("default").join(stringListWithNullValue);
        assertThat(join1,equalTo("guava#google#java#default"));
    }

    @Test
    public void joinOnTest_StringBuilder(){
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder1 = Joiner.on("#").useForNull("default").appendTo(stringBuilder, stringListWithNullValue);
        assertThat(stringBuilder1.toString(),equalTo("guava#google#java#default"));
        assertThat(stringBuilder,sameInstance(stringBuilder));
    }

    @Test
    public void joinOnTest_Writer(){
        try (FileWriter writer = new FileWriter(targetName)){
           Joiner.on("#").useForNull("default").appendTo(writer, stringListWithNullValue);

        } catch (IOException e) {
            fail("append to occur error ...");
        }
    }
}
