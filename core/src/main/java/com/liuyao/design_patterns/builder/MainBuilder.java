package com.liuyao.design_patterns.builder;

public class MainBuilder {

    /**
     * 构建者
     *  构建复杂对象
     *
     * @param args
     */
    public static void main(String[] args) {

        TerrainBuilder builder = new ComplexTerrainBuilder();
        Terrain t = builder.buildFort().buildMine().buildWall().build();

        Person p = new Person.PersonBuilder()
                .buildBasie(1, "刘瑶")
                .buildAge(18)
//                .buildSex('W')
//                .buildBody(55, 170)
//                .buildLoc("山东省")
                .buildFace("白皮肤")
                .buildHair("黄头发")
                .build();




    }

}
