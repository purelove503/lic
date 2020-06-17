package com.example.lic.reflect;

import com.example.lic.digui.FibonacciSequence;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author wy
 * @date 2020/6/16 17:32
 * @description
 */
public class ReflectExtendsTest extends FibonacciSequence {
    private static final long serialVersionUID = 1L;
    public final String publicField = "publicField";
    protected final String protectedField = "protectedField";
    private final String privateField = "privateField";
    /**
     * 获取所有属性
     * @throws Exception
     */
    @Test
    public void getFieldsTest() throws Exception {
        Class<?> clazz = Class.forName("com.example.lic.reflect.ReflectExtendsTest");
        System.out.println("===============取得本类的全部属性===============");
        // 取得本类的全部属性
        Field[] field = clazz.getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            // 权限修饰符
            int mo = field[i].getModifiers();
            String priv = Modifier.toString(mo);
            // 属性类型
            Class<?> type = field[i].getType();
            System.out.println(priv + " " + type.getName() + " " + field[i].getName() + ";");
        }

        System.out.println("==========取得本类的公共属性==========");
        // 取得本类的公共属性
        Field[] filed1 = clazz.getFields();
        for (int j = 0; j < filed1.length; j++) {
            // 权限修饰符
            int mo = filed1[j].getModifiers();
            String priv = Modifier.toString(mo);
            // 属性类型
            Class<?> type = filed1[j].getType();
            System.out.println(priv + " " + type.getName() + " " + filed1[j].getName() + ";");
        }
    }

    /**
     * 获取所有方法
     *
     * @param
     * @author wy
     * @date 2020/6/17 14:28
     * @return:
     */
    @Test
    public void getMethod() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.example.lic.reflect.ReflectExtendsTest");
        Method[] methods = clazz.getMethods();
        for (int i = 0; i < methods.length; i ++) {
            Class<?> returnType = methods[i].getReturnType();
            int temp = methods[i].getModifiers();
            System.out.print(Modifier.toString(temp) + " ");
            System.out.print(returnType.getName() + " ");
            System.out.print(methods[i].getName() + " ");
            System.out.print("(");
            Class<?> para[] = methods[i].getParameterTypes();
            for (int j = 0; j < para.length; ++j)  {
                System.out.print(para[j].getName() + "  " + "arg" + j);
                if (j < para.length - 1)  {
                    System.out.print(",");
                }
            }
            Class<?> exce[] = methods[i].getExceptionTypes();
            if (exce.length > 0) {
                System.out.print(")  throws  ");
                for (int k = 0; k < exce.length; ++k)  {
                    System.out.print(exce[k].getName() + "  ");
                    if  (k < exce.length - 1)  {
                        System.out.print(",");
                    }
                }
            } else {
                    System.out.print(")");
            }
            System.out.println();
        }
    }
}