package DesignPattern;

/*
 *[行为模式]
 * 模板模式
 * 模板模式确定了方法的执行顺序，定义了子类方法的默认实现。在父类中定义为final，不允许子类覆盖。
 * 模板模式包含了两个角色：
 * 抽象类（AbstractClass）：实现了模板方法，定义了算法的骨架。
 * 具体类（ConcreteClass)：实现抽象类中的抽象方法，来完成完整的算法。
 * jdk中的InputStream、OutputStream、Reader、Writer类都使用了模板模式
 * */

/** 抽象类 */
abstract class APerson{
    //抽象类定义整个流程骨架
    public final void prepareGotoSchool(){
         dressUp();
         eatBreakfast();
         takeThings();
    }
    //以下是不同子类根据自身特性完成的具体步骤
    protected abstract void dressUp();
    protected abstract void eatBreakfast();
    protected abstract void takeThings();
}

/** 具体实现类 */
class Student extends APerson{
    @Override
    protected void dressUp() {
         System.out.println("穿校服");
    }
    @Override
    protected void eatBreakfast() {
         System.out.println("吃妈妈做好的早饭");
    }

    @Override
    protected void takeThings() {
         System.out.println("背书包，带上家庭作业和红领巾");
    }
}

class Teacher extends APerson{
    @Override
    protected void dressUp() {
         System.out.println("穿工作服");
    }
    @Override
    protected void eatBreakfast() {
         System.out.println("做早饭，照顾孩子吃早饭");
    }

    @Override
    protected void takeThings() {
         System.out.println("带上昨晚准备的考卷");
    }
}

public class TemplatePatternEasy {
	public static void main(String[] args) {
		APerson person = new Student();
		person.prepareGotoSchool();
		
		person = new Teacher();
		person.prepareGotoSchool();
	}
}
