package com.test.idgenerate;

/**
 * 
 * Id 生成工具类
 * 
 * @author yuzhou
 * @date 2017年6月15日
 * @time 下午4:41:16
 *
 */
public class IdGenUtils {
    private static final String IG_GENERATOR_BEAN_NAME  = "idGenerator";

    private static IdGenerator idGenerator;

    /**
     * 生成Id
     * @param key
     * @return
     */
    public static Long generateId(String key) {
        return getGenerator().generateId(key);
    }
    
    private static IdGenerator getGenerator() {
        if(null == idGenerator) {
            idGenerator = ExtensionLoader.getSpringBean(IG_GENERATOR_BEAN_NAME, IdGenerator.class);
        }
        return idGenerator;
    }
    
}
