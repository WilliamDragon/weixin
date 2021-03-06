package com.gjl.weixin.java8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaTest {
    public static void main(String[] args) {
        List<UserModel> list = new ArrayList<>();
        ArrayList<UserModel> list11 = new ArrayList<>();
        List<UserTrade> list1 = new ArrayList<>();
        UserModel userModel=new UserModel(1,"张三",new Date(System.currentTimeMillis()),"1110",5);
        UserModel userModel1=new UserModel(2,"张三",new Date(System.currentTimeMillis()),"1111",6);
        UserModel userModel2=new UserModel(3,"李四",new Date(System.currentTimeMillis()),"1112",3);
        UserModel userModel3=new UserModel(4,"张三",new Date(System.currentTimeMillis()),"1113",4);
        UserTrade userTrade = new UserTrade(userModel,"小明",11);
        UserTrade userTrade1 = new UserTrade(userModel1,"小明",12);
        UserTrade userTrade2 = new UserTrade(userModel2,"小明",11);
        UserTrade userTrade3 = new UserTrade(userModel3,"小明",11);
        list.add(userModel);
        list.add(userModel1);
        list.add(userModel2);
        list.add(userModel3);
        list11.add(userModel);
        list11.add(userModel1);
        list11.add(userModel2);
        list11.add(userModel3);
        list1.add(userTrade);
        list1.add(userTrade1);
        list1.add(userTrade2);
        list1.add(userTrade3);

        //获取UserModel中以id作为list的集合
        /*List<Integer> idList = list.stream().map(UserModel::getId).collect(Collectors.toList());
        //获取UserModel 中以姓名集合
        List<String> nameList = list.stream().map(UserModel::getName).collect(Collectors.toList());
        //获取UserModel 中以性别为集合
        List<Integer> sexList = list.stream().map(UserModel::getSex).collect(Collectors.toList());
        //获取list中名字是张三的对象 否则 返回null
        UserModel userModel4 = list.stream().filter(x -> "张三".equals(x.getName())).findAny().orElse(null);
        //获取lis中名字，放在set中
        Set<String> nameSet = list.stream().map(UserModel::getName).collect(Collectors.toSet());
        List<Integer> pfListt = list.stream().map(x -> x.getSex() * x.getSex()).collect(Collectors.toList());*/
        /*Optional<Integer> reduce = list.stream().map(x -> 1).reduce(Integer::sum);
        System.out.println(reduce.get());*/
        //姓名是张三的并按年龄排序   默认升序
       /* List<UsereModel> sdf = list.stram().filter(x -> "张三".equals(x.getName()))
                .sorted((e1, e2) -> Integer.compare(e1.getSex(), e2.getSex()))
                .collect(Collectors.toList());
        System.out.println(sdf);
        //复杂排序
        多字段排序  先按姓名升序，姓名相同则按年龄升序
        list.sorted(Comparator.comparing(Student::getName).thenComparing(Student::getAge));

        List<UserModel> UserModelAsd = list1.stream().filter(x -> "小明".equals(x.getUserModel().getName()))
                .map(UserTrade::getUserModel)
                .sorted((e1, e2) -> e1.getName().compareTo(e2.getName())).collect(Collectors.toList());*/

        /*List<UserModel> collect = list1.stream().filter(x -> "小明".equals(x.getUserModel().getName()) || "夏利".equals(x.getUserModel().getName()))
                .map(UserTrade::getUserModel)
                .sorted(Comparator.comparing(UserModel::getName).reversed()).collect(Collectors.toList());*/
        //合并list集合
        List<UserModel> collect = list1.stream().filter(x -> "小明".equals(x.getUserModel().getName()))
                .map(UserTrade::getUserModel)
                .sorted(Comparator.comparing(UserModel::getName))
                .collect(Collectors.toList());
        List<UserModel> collect1 = Stream.of(list, list11).flatMap(x -> x.stream()).collect(Collectors.toList());
        System.out.println(collect1.size());

        Arrays.asList(2,9,43,7,3,0).stream().distinct().sorted().forEach(System.out::println);

    }

}
