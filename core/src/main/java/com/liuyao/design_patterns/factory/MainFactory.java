package com.liuyao.design_patterns.factory;

import com.liuyao.design_patterns.factory.F01_F02.F02_CarFactory;
import com.liuyao.design_patterns.factory.F01_F02.ICar;
import com.liuyao.design_patterns.factory.F01_F02.Moveable;
import com.liuyao.design_patterns.factory.F03_abstract.*;
import com.liuyao.design_patterns.factory.F04_springbeanIOC.Driver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainFactory {

    /**
     * 任意定制交通工具
     *  集成Moveable
     * 任意定制生产过程 F02
     *  Moveable XXXFactory.create();
     *  产品维度可扩展
     * 任意定制产品一族
     *  产品族可扩展 族中产品个数不可扩展
     *
     * 名词用抽象类 形容词用接口
     *
     * Spring IOC
     *  inverse of control ioc 控制反转 又叫DI dependency injection 依赖注入
     *  原来在程序中控制 spring在xml文件中控制
     *
     * PO: 数据库
     * VO: 传值
     * POJO: 普通
     * Bean: 一系列专门格式
     * 都是一回事
     *
     * @param args
     */
    public static void main(String[] args) {

        // F01
        Moveable m1 = new ICar();
        m1.go();
        // F02
        Moveable m2 = new F02_CarFactory().create();
        m2.go();
        // F03
        Car c = new Car();
        AK47 w = new AK47();
        w.shoot();
        Bread b = new Bread();
        b.printName();

        System.out.println(" ========================== 产品族 ===================");

        // 产品族
//        AbstractFactory abFactory = new ModernFactory();
        AbstractFactory abFactory = new MagicFactory();
        AbVehicle vehicle = abFactory.createVehicle();
        vehicle.go();
        AbFood food = abFactory.createFood();
        food.printName();
        AbWeapon weapon = abFactory.createWeapon();
        weapon.shoot();

        System.out.println(" ========================== spring ioc ===================");
        // spring ioc
        ApplicationContext context = new ClassPathXmlApplicationContext("config/spring.xml");
        Driver d = (Driver) context.getBean("driver");

    }
}
