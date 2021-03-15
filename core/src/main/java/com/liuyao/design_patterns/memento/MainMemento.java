package com.liuyao.design_patterns.memento;

public class MainMemento {

    /**
     * 备忘录
     *  记录快照
     *  存盘
     *      tank GameModel实现序列化接口Serializable
     *      transient 属性关键字 当序列化的时候会排除此属性 但反序列化则不会加载到此属性的内容
     *      见GameModel save() load()
     *
     *      使用 ProtpBuf序列化 体量会轻很多
     */
    public static void main(String[] args) {

    }
}
