package org.yframework.toolkit.asm;

import javassist.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 调度器工具类<BR>
 * ------------------------------------------<BR>
 * <BR>
 * Copyright©  : 2014-2015 by Flying_L<BR>
 * Author      : Flying_L <BR>
 * Date        : 2014-11-3<BR>
 * Description :<BR>
 * <p>
 * 根据指定的参数获取调度器实例：<BR>
 * 按照宿主方法b，以及父类方法a创建代理实例i，并上转型给类A。通过调用i的代理方法，调用b而达到代理作用。<BR>
 * <p>
 * 定义：<BR>
 * 宿主类：真正要执行的方法的所属类<BR>
 * 宿主方法：宿主类中的方法。<BR>
 * 父类：返回对象的类，调度器类<BR>
 * 父方法：父类中的方法，调用宿主方法时，代码就写成调用父方法达到调用目的<BR>
 * eg： 有一个类A包含方法a,有一个类B包含方法b。目的是要调用b。但却想拿到一个A对象，通过执行a,同样可以达到调用B的目的。<BR>
 * 场景：在Action中，原则要求任何一个方法都不是带参数的，因为使用反射直接调用过于消耗性能，所以先可以创建一个代理对象，然后执行代理对象中的方法，以达到调用Action中的方法。<BR>
 * 规则：宿主方法如果是静态的，则对应的父类方法和宿主方法的参数一一对应。如果宿主方法不是静态的，则父类方法中比宿主方法的参数多一个，即第一个参数是宿主方法中的this对象。<BR>
 * 理解：就是创建了一个新的类，继承于父类，并且重写了父方法，方法体是调用宿主方法。调用方式不是反射，而是直接调用。所以效率高于反射。<BR>
 * </p>
 */
public enum InvokerTool
{
    INSTANCE;

    /**
     * 生成的代理类对象实例 <br/>
     * 该代理类重写了指定的父类方法
     *
     * @param hostCls        宿主类
     * @param hostMethodName 宿主方法名称
     * @param superCls       父类：可以是一个接口、抽象类、普通类
     * @return 代理实例
     * @throws Exception
     */
    public <T> T newInvoker(Class<?> hostCls, String hostMethodName, Class<T> superCls) throws Exception
    {
        return this.newInvoker(hostCls, hostMethodName, null, superCls);
    }

    /**
     * 生成的代理类对象实例 <br/>
     * 该代理类重写了指定的父类方法
     *
     * @param hostCls         宿主类
     * @param hostMethodName  宿主方法名称
     * @param hostMethodClses 宿主方法参数数组
     * @param superCls        父类：可以是一个接口、抽象类、普通类
     * @return 代理实例
     * @throws Exception
     */
    public <T> T newInvoker(Class<?> hostCls, String hostMethodName, Class<?>[] hostMethodClses, Class<T> superCls) throws Exception
    {
        return this.newInvoker(hostCls, hostMethodName, hostMethodClses, superCls, hostMethodName);
    }

    /**
     * 生成的代理类对象实例 <br/>
     * 该代理类重写了指定的父类方法
     *
     * @param hostCls         宿主类
     * @param hostMethodName  宿主方法名称
     * @param hostMethodClses 宿主方法参数数组
     * @param superCls        父类：可以是一个接口、抽象类、普通类
     * @param superMethodName 父类方法名称
     * @return 代理实例
     * @throws Exception
     */
    public <T> T newInvoker(Class<?> hostCls, String hostMethodName, Class<?>[] hostMethodClses, Class<T> superCls, String superMethodName) throws Exception
    {
        Method hostMethod = hostCls.getMethod(hostMethodName, hostMethodClses);
        Method superMethod = null;
        int methodMod = hostMethod.getModifiers();
        if (Modifier.isStatic(methodMod))
        {
            superMethod = superCls.getMethod(superMethodName, hostMethodClses);
        }
        else
        {
            int len = hostMethodClses == null ? 0 : hostMethodClses.length;
            Class<?>[] superMethodClses = new Class<?>[len + 1];
            superMethodClses[0] = hostCls;
            for (int i = 0; i < len; i++)
            {
                superMethodClses[i + 1] = hostMethodClses[i];
            }
            superMethod = superCls.getMethod(superMethodName, superMethodClses);
        }
        return this.newInvoker(hostCls, hostMethod, superCls, superMethod);
    }

    /**
     * 生成的代理类对象实例 <br/>
     * 该代理类重写了指定的父类方法
     *
     * @param hostCls         宿主类
     * @param hostMethodName  宿主方法名称
     * @param superCls        父类：可以是一个接口、抽象类、普通类
     * @param superMethodName 父类方法名称
     * @return 代理实例
     * @throws Exception
     */
    public <T> T newInvoker(Class<?> hostCls, String hostMethodName, Class<T> superCls, String superMethodName) throws Exception
    {
        return this.newInvoker(hostCls, hostMethodName, null, superCls, superMethodName);
    }

    /**
     * 生成的代理类对象实例 <br/>
     * 该代理类重写了指定的父类方法
     *
     * @param hostCls          宿主类
     * @param hostMethodName   宿主方法名称
     * @param hostMethodClses  宿主方法参数数组
     * @param superCls         父类：可以是一个接口、抽象类、普通类
     * @param superMethodName  父类方法名称
     * @param superMethodClses 父类方法参数数组
     * @return 代理实例
     * @throws Exception
     */
    public <T> T newInvoker(Class<?> hostCls, String hostMethodName, Class<?>[] hostMethodClses, Class<T> superCls, String superMethodName, Class<?>[] superMethodClses) throws Exception
    {
        Method hostMethod = hostCls.getMethod(hostMethodName, hostMethodClses);
        Method superMethod = superCls.getMethod(superMethodName, superMethodClses);
        return this.newInvoker(hostCls, hostMethod, superCls, superMethod);
    }

    /**
     * 生成的代理类对象实例 <br/>
     * 该代理类重写了指定的superMethod
     *
     * @param hostCls     宿主类
     * @param hostMethod  宿主方法
     * @param superCls    父类：可以是一个接口、抽象类、普通类
     * @param superMethod 父类方法
     * @return 代理实例
     * @throws Exception
     */
    public <T> T newInvoker(Class<?> hostCls, Method hostMethod, Class<T> superCls, Method superMethod) throws Exception
    {
        if (superMethod.getDeclaringClass().equals(superCls) == false)
        {
            throw new Exception("super method '" + superMethod.getName() + "' is not declaring in super class '" + superCls.getCanonicalName() + "'!");
        }
        if (hostMethod.getDeclaringClass().equals(hostCls) == false && hostMethod.getDeclaringClass().equals(hostCls.getSuperclass()) == false)
        {
            throw new Exception("host method '" + hostMethod.getName() + "' is not declaring in host class '" + hostCls.getCanonicalName() + "'!");
        }
        return this.createInstance(hostCls, new Method[]{hostMethod}, superCls, new Method[]{superMethod});
    }

    /**
     * 生成的代理类对象实例 <br/>
     * 该代理类重写了指定的superMethods所有方法，注意superMethods和hostMethods是需要一一对应的。
     *
     * @param hostCls         宿主类
     * @param hostExecutables 宿主方法数组
     * @param superCls        父类：可以是一个接口、抽象类、普通类
     * @param superMethods    父类方法数组
     * @return 代理实例
     * @throws Exception
     */
    public <T> T newInvoker(Class<?> hostCls, Executable[] hostExecutables, Class<T> superCls, Method[] superMethods) throws Exception
    {
        if (superMethods == null || hostExecutables == null || superMethods.length != hostExecutables.length)
        {
            throw new Exception("count of super method is not equal with count of host method!");
        }
        for (Method method : superMethods)
        {
            if (method.getDeclaringClass().equals(superCls) == false)
            {
                throw new Exception("super method '" + method.getName() + "' is not declaring in super class '" + superCls.getCanonicalName() + "'!");
            }
        }
        for (Executable executable : hostExecutables)
        {
            if (executable instanceof Method && executable.getDeclaringClass().isAssignableFrom(hostCls) == false)
            {
                throw new Exception("super method '" + executable.getName() + "' is not declaring in host class '" + hostCls.getCanonicalName() + "'!");
            }
        }
        return this.createInstance(hostCls, hostExecutables, superCls, superMethods);
    }

    /**
     * 根据传入的方法创建快速调用器。使用默认使用Invoker作为父类
     *
     * @param method 代理方法对象
     * @return 调用器
     */
    public InvokerIntf newInvoker(Method method)
    {
        InvokerIntf result = null;
        try
        {
            String proxyClsName = this.getProxyClsName(InvokerIntf.class, method.getDeclaringClass(), new Method[]{method});
            Class<?> resultCls = null;
            try
            {
                resultCls = Class.forName(proxyClsName);
            }
            catch (ClassNotFoundException e)
            {
                ClassPool cp = ClassPool.getDefault();
                cp.insertClassPath(new ClassClassPath(InvokerIntf.class));
                CtClass cc = cp.makeClass(proxyClsName);
                cc.addInterface(cp.get(InvokerIntf.class.getName()));
                cc.addMethod(CtMethod.make(this.createMethod(method), cc));
                resultCls = cc.toClass();
            }
            result = (InvokerIntf) resultCls.newInstance();
        }
        catch (Throwable e)
        {
            if (e instanceof RuntimeException)
            {
                throw (RuntimeException) e;
            }
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 创建代理类实例
     *
     * @param hostCls
     * @param hostExecutables
     * @param superCls
     * @param superMethods
     * @return
     */
    private <T> T createInstance(Class<?> hostCls, Executable[] hostExecutables, Class<T> superCls, Method[] superMethods) throws Exception
    {
        String proxyClsName = this.getProxyClsName(superCls, hostCls, hostExecutables);
        Class<T> proxyCls = this.createClass(proxyClsName, hostCls, hostExecutables, superCls, superMethods);
        return proxyCls.newInstance();
    }

    ;

    /**
     * 创建代理类
     *
     * @param hostCls         宿主类
     * @param hostExecutables 宿主方法数组
     * @param superCls        父类
     * @param superMethods    父类方法数组
     * @return
     * @throws Exception
     */
    private <T> Class<T> createClass(String proxyClsName, Class<?> hostCls, Executable[] hostExecutables, Class<T> superCls, Method[] superMethods) throws Exception
    {
        Class<?> result = null;
        try
        {
            int lenI = superMethods == null ? 0 : superMethods.length;
            if (lenI != (hostExecutables == null ? 0 : hostExecutables.length))
            {
                throw new Exception("create proxy class error: count of method is error!");
            }
            try
            {
                result = Class.forName(proxyClsName);
            }
            catch (ClassNotFoundException e)
            {
                ClassPool cp = ClassPool.getDefault();
                cp.insertClassPath(new ClassClassPath(superCls));
                CtClass cc = cp.makeClass(proxyClsName);
                if (superCls.isInterface())
                {
                    cc.addInterface(cp.get(superCls.getName()));
                }
                else
                {
                    cc.setSuperclass(cp.get(superCls.getName()));
                }

                for (int i = 0; i < lenI; i++)
                {
                    Method superMethod = superMethods[i];
                    if (Modifier.isFinal(superMethod.getModifiers()))
                    {
                        continue;
                    }
                    if (hostExecutables[i] instanceof Method)
                    {
                        cc.addMethod(CtMethod.make(this.createMethod(hostCls, (Method) hostExecutables[i], superCls, superMethod), cc));
                    }
                    else if (hostExecutables[i] instanceof Constructor)
                    {
                        cc.addMethod(CtMethod.make(this.createMethod((Constructor) hostExecutables[i], superCls, superMethod), cc));
                    }
                    else
                    {
                        throw new Exception("create proxy class error: Executable class is not support" + hostExecutables[i].getClass() + "!");
                    }

                }
                result = cc.toClass();
            }
        }
        catch (Throwable e)
        {
            //多线程环境，A线程在开始进来的时候并没有创建proxyClsName类，但当后来创建class的时候，proxyClsName已经被另外一个线程创建出来了。此时将会抛出异常。
            try
            {
                result = Class.forName(proxyClsName);
            }
            catch (ClassNotFoundException e2)
            {
                if (e instanceof RuntimeException)
                {
                    throw (RuntimeException) e;
                }
                throw new RuntimeException(e);
            }
        }
        return (Class<T>) result;
    }

    /**
     * 创建方法，返回方法代码，返回值如下<br>
     * public methodReturnCls/void methodName([hostCls curObj][, methodArgClses0 arg0, , methodArgClses1 arg1 ...])<br>
     * {<br>
     * [return] hostCls/curObj.methodName([arg0, arg1, ...]);<br>
     * }<br>
     * <br>
     *
     * @param hostCls    宿主类
     * @param hostMethod 宿主方法
     * @param superCls   代理父类
     * @param hostMethod 代理父类方法
     * @return
     * @throws Exception
     */
    private String createMethod(Class<?> hostCls, Method hostMethod, Class<?> superCls, Method superMethod) throws Exception
    {
        StringBuilder argCode = new StringBuilder();
        StringBuilder callArgCode = new StringBuilder();
        Class<?>[] superMethodArgClses = superMethod.getParameterTypes();
        Class<?>[] hostMethodArgClses = hostMethod.getParameterTypes();
        Class<?> superReturnCls = superMethod.getReturnType();
        Class<?> hostReturnCls = hostMethod.getReturnType();
        int modifiers = hostMethod.getModifiers();
        boolean isStatic = Modifier.isStatic(modifiers);

        if (Modifier.isPublic(modifiers) == false)
        {
            throw new Exception("create proxy method error: method modifier is not public!");
        }

        int lenArgHost = hostMethodArgClses == null ? 0 : hostMethodArgClses.length;
        int lenArgSuper = superMethodArgClses == null ? 0 : superMethodArgClses.length;
        if ((isStatic && lenArgHost != lenArgSuper) || (isStatic == false && lenArgSuper - 1 != lenArgHost))
        {
            throw new Exception("create proxy method error: arguments is error!");
        }

        for (int i = isStatic ? 0 : 1; i < lenArgSuper; i++)
        {
            argCode.append(", ");
            argCode.append(superMethodArgClses[i].getCanonicalName());
            argCode.append(" arg");
            argCode.append(i);

            if (isStatic)
            {
                callArgCode.append(", ");
                callArgCode.append(this.castCls("arg" + i, superMethodArgClses[i], hostMethodArgClses[i]));
            }
            else if (i > 0)
            {
                callArgCode.append(", ");
                callArgCode.append(this.castCls("arg" + i, superMethodArgClses[i], hostMethodArgClses[i - 1]));
            }
        }

        boolean isVoid = void.class.equals(superReturnCls);
        StringBuilder result = new StringBuilder();
        // 方法声明
        result.append("public ");
        result.append(superReturnCls.getCanonicalName() + " ");
        result.append(superMethod.getName());
        result.append("(");
        if (isStatic)
        {
            if (argCode.length() > 0)
            {
                result.append(argCode.delete(0, 2));
            }
        }
        else
        {
            result.append(superMethodArgClses[0].getCanonicalName());
            result.append(" curObj");
            if (argCode.length() > 0)
            {
                result.append(argCode);
            }
        }
        result.append("){");
        // 方法体

        if (isVoid == false)
        {
            result.append(hostReturnCls.getCanonicalName());
            result.append(" result = ");
        }
        if (isStatic)
        {
            result.append(hostCls.getCanonicalName());
        }
        else
        {
            result.append(this.castCls("curObj", superMethodArgClses[0], hostCls));
        }
        result.append(".");
        result.append(hostMethod.getName());
        result.append("(");
        if (callArgCode.length() > 0)
        {
            result.append(callArgCode.delete(0, 2));
        }
        result.append(");");
        if (isVoid == false)
        {
            result.append("return ");
            result.append(this.castCls("result", hostReturnCls, superReturnCls));
            result.append(";");
        }
        result.append("}");
        return result.toString();
    }

    /**
     * 创建方法，返回方法代码，返回值如下<br>
     * public hostCls methodName([, methodArgClses0 arg0, , methodArgClses1 arg1 ...])<br>
     * {<br>
     * [return] new hostCls([arg0, arg1, ...]);<br>
     * }<br>
     * <br>
     *
     * @param hostConstructor 宿主构造器
     * @param superCls        代理父类
     * @param superMethod     代理父类方法
     * @return
     * @throws Exception
     */
    private <T> String createMethod(Constructor<T> hostConstructor, Class<?> superCls, Method superMethod) throws Exception
    {
        StringBuilder argCode = new StringBuilder();
        StringBuilder callArgCode = new StringBuilder();
        Class<?>[] superMethodArgClses = superMethod.getParameterTypes();
        Class<?>[] hostMethodArgClses = hostConstructor.getParameterTypes();
        Class<?> superReturnCls = superMethod.getReturnType();

        int modifiers = hostConstructor.getModifiers();

        if (Modifier.isPublic(modifiers) == false)
        {
            throw new Exception("create proxy method error: method modifier is not public!");
        }

        int lenArgHost = hostMethodArgClses == null ? 0 : hostMethodArgClses.length;
        int lenArgSuper = superMethodArgClses == null ? 0 : superMethodArgClses.length;
        if (lenArgHost != lenArgSuper)
        {
            throw new Exception("create proxy method error: arguments is error!");
        }

        for (int i = 0, lenI = lenArgSuper; i < lenI; i++)
        {
            argCode.append(", ");
            argCode.append(superMethodArgClses[i].getCanonicalName());
            argCode.append(" arg");
            argCode.append(i);

            callArgCode.append(", ");
            callArgCode.append(this.castCls("arg" + i, superMethodArgClses[i], hostMethodArgClses[i]));
        }

        boolean isVoid = void.class.equals(superReturnCls);
        StringBuilder result = new StringBuilder();
        // 方法声明
        result.append("public ");
        result.append(superReturnCls.getCanonicalName() + " ");
        result.append(superMethod.getName());
        result.append("(");
        if (argCode.length() > 0)
        {
            result.append(argCode.delete(0, 2));
        }
        result.append("){");
        // 方法体

        result.append("return new ");
        result.append(hostConstructor.getDeclaringClass().getCanonicalName());
        result.append("(");
        if (callArgCode.length() > 0)
        {
            result.append(callArgCode.delete(0, 2));
        }
        result.append(");");
        result.append("}");
        return result.toString();
    }

    /**
     * 创建方法，返回方法代码，返回值如下<br>
     * public java.lang.object invoke(Object curObj, Object... args)<br>
     * {<br>
     * return hostCls/curObj.methodName(args);<br>
     * }<br>
     * <br>
     *
     * @param hostMethod 宿主方法
     * @return
     * @throws Exception
     */
    private String createMethod(Method hostMethod) throws Exception
    {
        StringBuilder callArgCode = new StringBuilder();
        Class<?>[] hostMethodArgClses = hostMethod.getParameterTypes();
        Class<?> hostReturnCls = hostMethod.getReturnType();
        Class<?> hostCls = hostMethod.getDeclaringClass();
        for (int i = 0, lenI = hostMethodArgClses.length; i < lenI; i++)
        {
            callArgCode.append(", ");
            callArgCode.append(this.castCls("args[" + i + "]", Object.class, hostMethodArgClses[i]));
        }

        boolean isVoid = void.class.equals(hostReturnCls);
        StringBuilder result = new StringBuilder();

        // 方法声明
        result.append("public java.lang.Object invoke(java.lang.Object curObj, java.lang.Object[] args){ ");
        // 方法体

        if (isVoid == false)
        {
            result.append(hostReturnCls.getCanonicalName());
            result.append(" result = ");
        }
        if (Modifier.isStatic(hostMethod.getModifiers()))
        {
            result.append(hostCls.getCanonicalName());
        }
        else
        {
            result.append(this.castCls("curObj", Object.class, hostCls));
        }
        result.append(".");
        result.append(hostMethod.getName());
        result.append("(");
        if (callArgCode.length() > 0)
        {
            result.append(callArgCode.delete(0, 2));
        }
        result.append(");");
        if (isVoid == false)
        {
            result.append("return ");
            result.append(this.castCls("result", hostReturnCls, Object.class));
            result.append(";");
        }
        else
        {
            result.append("return null; ");
        }
        result.append("}");
        return result.toString();
    }

    /**
     * 强制转换对象类
     *
     * @param arg
     * @param fromCls
     * @param toCls
     * @return
     * @throws Exception
     */
    private String castCls(String arg, Class<?> fromCls, Class<?> toCls) throws Exception
    {
        StringBuilder result = new StringBuilder();
        arg = arg.trim();
        arg = "(" + arg + ")";
        if (fromCls.equals(toCls))
        {
            result.append(arg);
        }
        else if (fromCls.isPrimitive() && toCls.isPrimitive() == false)
        {
            result.append("((");
            result.append(toCls.getCanonicalName());
            result.append(")");
            result.append(this.castCls(arg, fromCls, true));
            result.append(")");
        }
        else if (fromCls.isPrimitive() == false && toCls.isPrimitive())
        {
            Class<?> wraperCls = this.getWraperCls(toCls);
            StringBuilder wraperArg = new StringBuilder();
            wraperArg.append("((");
            wraperArg.append(wraperCls.getCanonicalName());
            wraperArg.append(")");
            wraperArg.append(arg);
            wraperArg.append(")");
            result.append(this.castCls(wraperArg.toString(), wraperCls, false));
        }
        else
        {
            result.append("((");
            result.append(toCls.getCanonicalName());
            result.append(")");
            result.append(arg);
            result.append(")");
        }
        return result.toString();
    }

    /**
     * 强制转换对象类型，基本类和对应包装类之间的互相转化
     *
     * @param arg
     * @param cls
     * @param isPrimitive2Wraper true--从基本类转化为包装来，false--从包装类转化为基本类
     * @return
     * @throws Exception
     */
    private StringBuilder castCls(String arg, Class<?> cls, boolean isPrimitive2Wraper) throws Exception
    {
        StringBuilder result = new StringBuilder();
        if (isPrimitive2Wraper)
        {
            if (cls.equals(int.class))
            {
                result.append("(java.lang.Integer.valueOf(");
                result.append(arg);
                result.append("))");
            }
            else if (cls.equals(short.class))
            {
                result.append("(java.lang.Short.valueOf(");
                result.append(arg);
                result.append("))");
            }
            else if (cls.equals(long.class))
            {
                result.append("(java.lang.Long.valueOf(");
                result.append(arg);
                result.append("))");
            }
            else if (cls.equals(float.class))
            {
                result.append("(java.lang.Float.valueOf(");
                result.append(arg);
                result.append("))");
            }
            else if (cls.equals(double.class))
            {
                result.append("(java.lang.Double.valueOf(");
                result.append(arg);
                result.append("))");
            }
            else if (cls.equals(byte.class))
            {
                result.append("(java.lang.Byte.valueOf(");
                result.append(arg);
                result.append("))");
            }
            else if (cls.equals(char.class))
            {
                result.append("(java.lang.Character.valueOf(");
                result.append(arg);
                result.append("))");
            }
            else if (cls.equals(boolean.class))
            {
                result.append("(java.lang.Boolean.valueOf(");
                result.append(arg);
                result.append("))");
            }
            else
            {
                throw new Exception("can't convert class form primitive to wraper, class name is " + cls.getCanonicalName());
            }
        }
        else
        {
            if (cls.equals(Integer.class))
            {
                result.append("((");
                result.append(arg);
                result.append(").intValue())");
            }
            else if (cls.equals(Short.class))
            {
                result.append("((");
                result.append(arg);
                result.append(").shortValue())");
            }
            else if (cls.equals(Long.class))
            {
                result.append("((");
                result.append(arg);
                result.append(").longValue())");
            }
            else if (cls.equals(Float.class))
            {
                result.append("((");
                result.append(arg);
                result.append(").floatValue())");
            }
            else if (cls.equals(Double.class))
            {
                result.append("((");
                result.append(arg);
                result.append(").doubleValue())");
            }
            else if (cls.equals(Byte.class))
            {
                result.append("((");
                result.append(arg);
                result.append(").byteValue())");
            }
            else if (cls.equals(Character.class))
            {
                result.append("((");
                result.append(arg);
                result.append(").charValue())");
            }
            else if (cls.equals(Boolean.class))
            {
                result.append("((");
                result.append(arg);
                result.append(").booleanValue())");
            }
            else
            {
                throw new Exception("can't convert class form wraper to primitive, class name is " + cls.getCanonicalName());
            }
        }
        return result;
    }

    /**
     * 获取基本类的包装类
     *
     * @param primitiveCls
     * @return
     */
    private Class<?> getWraperCls(Class<?> primitiveCls)
    {
        Class<?> result = null;
        if (primitiveCls.equals(int.class))
        {
            result = Integer.class;
        }
        else if (primitiveCls.equals(short.class))
        {
            result = Short.class;
        }
        else if (primitiveCls.equals(long.class))
        {
            result = Long.class;
        }
        else if (primitiveCls.equals(float.class))
        {
            result = Float.class;
        }
        else if (primitiveCls.equals(double.class))
        {
            result = Double.class;
        }
        else if (primitiveCls.equals(byte.class))
        {
            result = Byte.class;
        }
        else if (primitiveCls.equals(char.class))
        {
            result = Character.class;
        }
        else if (primitiveCls.equals(boolean.class))
        {
            result = Boolean.class;
        }
        else
        {
            result = primitiveCls;
        }
        return result;
    }

    /**
     * 获取代理类名称
     *
     * @param superCls
     * @param hostCls
     * @param executables
     * @return
     */
    private String getProxyClsName(Class<?> superCls, Class<?> hostCls, Executable[] executables)
    {
        StringBuilder proxyClsName = new StringBuilder();
        proxyClsName.append(superCls.getCanonicalName());
        proxyClsName.append("$");
        int hashNum = hostCls.hashCode();
        proxyClsName.append(hashNum);
        proxyClsName.append("$");
        hashNum = Arrays.hashCode(executables);
        proxyClsName.append(hashNum > 0 ? 0 : 1);
        proxyClsName.append(Math.abs(hashNum));
        return proxyClsName.toString();
    }
}
