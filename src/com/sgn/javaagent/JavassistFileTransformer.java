package com.sgn.javaagent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import org.objectweb.asm.Opcodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavassistFileTransformer implements ClassFileTransformer, Opcodes {

    static final Logger logger = LoggerFactory.getLogger(JavassistFileTransformer.class);

    @Override
    public byte[] transform(ClassLoader loader, String className, Class classBeingRedefined,
            ProtectionDomain protectionDomain, byte[] classfileBuffer)
            throws IllegalClassFormatException {
    	
        logger.info("class file transformer invoked for className: {}", className);

        if (className.equals("com/sgn/simpleapp/MyUser")) {
        	
        	try{
        		logger.info("Instrumenting: com/sgn/simpleapp/MyUser");
	        	ClassPool pool = ClassPool.getDefault();
	        	CtClass cc = pool.get("com.sgn.simpleapp.MyUser");
	        	CtMethod m = cc.getDeclaredMethod("getName");
	        	m.insertBefore("{ logger.info(\"Intrumentation, bitch\"); }");
	        	//cc.writeFile();
	        	byte[] modded = cc.toBytecode();
	        	logger.info("Done");
	        	return modded;
        	}catch(Exception e){
        		logger.error(e.getMessage(),e);
        	}

        	
//        	CtMethod cm = ... ;
//        	cm.instrument(
//        	    new ExprEditor() {
//        	        public void edit(MethodCall m)
//        	                      throws CannotCompileException
//        	        {
//        	            if (m.getClassName().equals("Point")
//        	                          && m.getMethodName().equals("move"))
//        	                m.replace("{ $1 = 0; $_ = $proceed($$); }");
//        	        }
//        	    });
        }

        return classfileBuffer;
    }

}
