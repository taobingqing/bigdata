package com.loombook;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.annotation.Nonnull;

/**
 * lombok 的  @Builder  @Getter  @NonNull 还是会报空指针异常没用
 *   @Setter  的用法
 */
@Builder
public class Course {

    @Getter
    @Setter
    private String id;
    private String name;

    public static void main(String[] args) {
        Course math = Course.builder().id("01").name("math").build();
        System.out.println(math.id+"=="+math.name);
        math.setId("02");
        System.out.println(math.id+"=="+math.name);
        math.testNonNull(null);
        math.notNull(null);

    }

    public void testNonNull(@NonNull String s){
        System.out.println(s);
    }
    public void notNull(String s ){
        if (s !=null){
            System.out.println(s);
       }

    }
}
