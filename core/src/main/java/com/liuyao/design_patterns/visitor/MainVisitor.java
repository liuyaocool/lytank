package com.liuyao.design_patterns.visitor;

public class MainVisitor {

    /**
     * 访问者模式
     *  访问结构固定的
     *
     * 应用
     *  编译器 语法分析
     *
     * @param args
     */
    public static void main(String[] args) {

        Computer com = new Computer();

        com.accept(new CompanyVistitor());

        com.accept(new StudentVistitor());

    }
}

class Computer {
    ComputerPart cup = new CPU();
    ComputerPart mem = new Mem();
    ComputerPart board = new Board();

    public void accept(Vistitor v){
       cup.accept(v);
       mem.accept(v);
       board.accept(v);
    }

}

abstract class Vistitor{

    public abstract void visitCpu(ComputerPart cpu);

    public abstract void visitMem(ComputerPart mem);

    public abstract void visitBoard(ComputerPart board);
}
abstract class ComputerPart {
    abstract void accept(Vistitor v);
    abstract double getPrice();
}
class CPU extends ComputerPart {

    @Override
    void accept(Vistitor v) {
        v.visitCpu(this);
    }

    @Override
    double getPrice() {
        return 1000;
    }
}

class Mem extends ComputerPart {

    @Override
    void accept(Vistitor v) {
        v.visitMem(this);
    }

    @Override
    double getPrice() {
        return 500;
    }
}

class Board extends ComputerPart {

    @Override
    void accept(Vistitor v) {
        v.visitBoard(this);

    }

    @Override
    double getPrice() {
        return 100;
    }
}

class CompanyVistitor extends Vistitor {

    @Override
    public void visitCpu(ComputerPart cpu) {
        System.out.println("Company cpu price: " + cpu.getPrice() * 0.92);
    }

    @Override
    public void visitMem(ComputerPart mem) {
        System.out.println("Company cpu price: " + mem.getPrice() * 0.92);

    }

    @Override
    public void visitBoard(ComputerPart board) {
        System.out.println("Company cpu price: " + board.getPrice() * 0.92);

    }
}


class StudentVistitor extends Vistitor {

    @Override
    public void visitCpu(ComputerPart cpu) {
        System.out.println("Student cpu price: " + cpu.getPrice() * 0.8);
    }

    @Override
    public void visitMem(ComputerPart mem) {
        System.out.println("Student cpu price: " + mem.getPrice() * 0.8);

    }

    @Override
    public void visitBoard(ComputerPart board) {
        System.out.println("Student cpu price: " + board.getPrice() * 0.8);

    }
}
