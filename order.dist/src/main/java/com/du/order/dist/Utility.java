package com.du.order.dist;



import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.du.order.dist.model.base.BaseModel;
import com.du.order.dist.model.util.ValidationError;


public abstract class Utility {
	static Logger logger = LoggerFactory.getLogger(Utility.class);
	
	/**
	 * 
	 * @param fromObj
	 * @param toObj
	 * @param recursiveCopy
	 * @param exclude eğer true ise propertyNames'de geçenleri kopyalar, false propertyNames'de geçenleri kopyalamaz, null ise exclude yapar
	 * @param propertyNames
	 * @throws Exception 
	 */
	public static void copyProperties(Object fromObj, Object toObj, boolean recursiveCopy, boolean exclude, boolean copyNulls, String ... propertyNames) throws Exception {
		
		if (propertyNames == null) {
			propertyNames = new String [0];
		}
		
		if (exclude) { //eğer exclude yapılacaksa
			String [] nativeExcludedPropertyNames = new String []{"id", "oid", "lastUpdater",  "creatorPerson", "createdTime", "lastUpdated"/*, "lastUpdated"*/}; //lastUpdated kaldırıldı, ekrandan gelen lastUpdated bilgisinin kopyalanması gerekiyor
			propertyNames = (String[]) ArrayUtils.addAll(propertyNames, nativeExcludedPropertyNames);
		}
		
		PropertyDescriptor pFrom[] = PropertyUtils.getPropertyDescriptors(fromObj.getClass());
		PropertyDescriptor pTo[] = PropertyUtils.getPropertyDescriptors(toObj.getClass());
		
		for (int i = 0; i < pFrom.length; i++) {
			PropertyDescriptor pDesc = pFrom[i];
			String propertyName = pDesc.getName(); 
			
			if (exclude) {
				/**
				 * Kopyalanmayacak alanlardaysa veya setter metodu yoksa devam it
				 */
				if (ArrayUtils.indexOf(propertyNames, propertyName) >= 0 || PropertyUtils.getWriteMethod(pDesc) == null) {
					continue;
				}				
			} else {
				/**
				 * Kopyalanacak alanlarda değilse veya setter metodu yoksa
				 */
				if (ArrayUtils.indexOf(propertyNames, propertyName) < 0 || PropertyUtils.getWriteMethod(pDesc) == null) {
					continue;
				}
			}
			
			try {
				if (containPropertyDescriptor(pDesc, pTo) && !propertyName.equals("class")
//					 && !ClassUtils.isAssignable(pDesc.getPropertyType(), AModel.class) && !pDesc.getPropertyType().isAssignableFrom(List.class) && !pDesc.getPropertyType().isAssignableFrom(Set.class)
					){
					
					Object fromObjField = PropertyUtils.getProperty(fromObj, pDesc.getName());
					Class<?> fromObjFieldClass = PropertyUtils.getPropertyType(fromObj, pDesc.getName());
					PropertyDescriptor pDescCorr = getCorrespondentAttribute(pTo, pFrom[i].getName());

					if(fromObjField == null) {
						if(!copyNulls){continue;}
						PropertyUtils.setProperty(toObj, pFrom[i].getName(), null);
						continue;
					}
					if( pDescCorr != null && ClassUtils.isAssignable(fromObjFieldClass, pDescCorr.getPropertyType())){
						PropertyUtils.setProperty(toObj, pFrom[i].getName(),fromObjField);
					} else {
						logger.info("\"" +pFrom[i].getName()+"\" field isimli özellik aynı diger objeye assignable değil. Manuel olarak set ediniz.");
					}
				}
			} catch (Exception e) {
				throw e;
			}
		}		
	}
/**
 * @author deniz.ozen
 * @param fromObj
 * @param toObj
 * @param copyNulls
 * @param propertyNames
 * Amodel extends and collection instances will be excluded form copy action
 * @throws Exception 
 */
public static void copyPrimitiveProperties(Object fromObj, Object toObj, boolean copyNulls, String ... propertyNames) throws Exception {
		
		if (propertyNames == null) {
			propertyNames = new String [0];
		}
		
		if (true) { //eğer exclude yapılacaksa
			String [] nativeExcludedPropertyNames = new String []{"oid", "created", "lastUpdated"}; //lastUpdated kaldırıldı, ekrandan gelen lastUpdated bilgisinin kopyalanması gerekiyor
			propertyNames = (String[]) ArrayUtils.addAll(propertyNames, nativeExcludedPropertyNames);
		}
		
		PropertyDescriptor pFrom[] = PropertyUtils.getPropertyDescriptors(fromObj.getClass());
		PropertyDescriptor pTo[] = PropertyUtils.getPropertyDescriptors(toObj.getClass());
		
		for (int i = 0; i < pFrom.length; i++) {
			PropertyDescriptor pDesc = pFrom[i];
			String propertyName = pDesc.getName(); 
			
			if (true) {
				/**
				 * Kopyalanmayacak alanlardaysa veya setter metodu yoksa devam it
				 */
				if (ArrayUtils.indexOf(propertyNames, propertyName) >= 0 || PropertyUtils.getWriteMethod(pDesc) == null) {
					continue;
				}				
			} 
			try {
				if (containPropertyDescriptor(pDesc, pTo) && !propertyName.equals("class")){
					
					Object fromObjField = PropertyUtils.getProperty(fromObj, pDesc.getName());
					Class<?> fromObjFieldClass = PropertyUtils.getPropertyType(fromObj, pDesc.getName());
					PropertyDescriptor pDescCorr = getCorrespondentAttribute(pTo, pFrom[i].getName());

					if(fromObjField == null) {
						if(!copyNulls){continue;}
						PropertyUtils.setProperty(toObj, pFrom[i].getName(), null);
						continue;
					}
					if( pDescCorr != null && ClassUtils.isAssignable(fromObjFieldClass, pDescCorr.getPropertyType())){
						if(/*fromObjField instanceof AModel || */fromObjField instanceof Collection){
							continue;
						}
						PropertyUtils.setProperty(toObj, pFrom[i].getName(),fromObjField);
					} else {
						logger.info("\"" +pFrom[i].getName()+"\" field isimli özellik aynı diger objeye assignable değil. Manuel olarak set ediniz.");
					}
				}
			} catch (Exception e) {
				throw e;
			}
		}		
	}
	
	public static PropertyDescriptor getCorrespondentAttribute (PropertyDescriptor[] pTo, String attributeName){
		for (PropertyDescriptor propertyDescriptor : pTo) {
			if(propertyDescriptor.getName().equals(attributeName)){
				return propertyDescriptor;
			}
		}
		return null;
	}
	public static boolean containPropertyDescriptor(PropertyDescriptor pFrom,
			PropertyDescriptor[] pTo) {
		for (int i = 0; i < pTo.length; i++) {
			if (pTo[i].getName().equals(pFrom.getName())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * YARIM KALDI RECURSİVE TEST EDİLMEDİ
	 * @param fromObj
	 * @param toObj
	 * @param recursiveCopy
	 * @param exclude eğer true ise propertyNames'de geçenleri kopyalar, false propertyNames'de geçenleri kopyalamaz, null ise exclude yapar
	 * @param propertyNames
	 * @throws Exception 
	 */
	public static <T extends BaseModel> T copyPropertiesSession(T fromObj, T toObj, boolean recursiveCopy, boolean exclude, boolean copyNulls, String ... propertyNames) throws Exception {

		if (propertyNames == null) {
			propertyNames = new String [0];
		}
		
		if (true) { //eğer exclude yapılacaksa
			String [] nativeExcludedPropertyNames = new String []{"id", "oid", "lastUpdater",  "creatorPerson", "lastUpdated"/*, "lastUpdated"*/}; //lastUpdated kaldırıldı, ekrandan gelen lastUpdated bilgisinin kopyalanması gerekiyor
			propertyNames = (String[]) ArrayUtils.addAll(propertyNames, nativeExcludedPropertyNames);
		}
		
		PropertyDescriptor pFrom[] = PropertyUtils.getPropertyDescriptors(fromObj.getClass());
		PropertyDescriptor pTo[] = PropertyUtils.getPropertyDescriptors(toObj.getClass());
		
		for (int i = 0; i < pFrom.length; i++) {
			PropertyDescriptor pDesc = pFrom[i];
			String propertyName = pDesc.getName(); 
			

			
			if (true) {
				/**
				 * Kopyalanmayacak alanlardaysa veya setter metodu yoksa devam et
				 */
				if (ArrayUtils.indexOf(propertyNames, propertyName) >= 0 || PropertyUtils.getWriteMethod(pDesc) == null) {
					continue;
				}				
			} 
			try {
				if (Utility.containPropertyDescriptor(pDesc, pTo) && !propertyName.equals("class")
					){
					if(isManyToOne(fromObj.getClass(), propertyName)){
						Object fromObjField = PropertyUtils.getProperty(fromObj, pDesc.getName());
						Class<?> fromObjFieldClass = PropertyUtils.getPropertyType(fromObj, pDesc.getName());
						PropertyDescriptor pDescCorr = Utility.getCorrespondentAttribute(pTo, pFrom[i].getName());
						if( pDescCorr != null && ClassUtils.isAssignable(fromObjFieldClass, pDescCorr.getPropertyType())){
							if(fromObjField == null) {
								if(!copyNulls){continue;}
								PropertyUtils.setProperty(toObj, pFrom[i].getName(), null);
								continue;
							}
							PropertyUtils.setProperty(toObj, pFrom[i].getName(), fromObjField); }
							continue;
						}
					
					BaseModel fromObjModel = null;
					List<BaseModel> fromObjList = null;
					boolean model = false;
					boolean list = false;
					
					Object fromObjField = PropertyUtils.getProperty(fromObj, pDesc.getName());
					Class<?> fromObjFieldClass = PropertyUtils.getPropertyType(fromObj, pDesc.getName());
					PropertyDescriptor pDescCorr = Utility.getCorrespondentAttribute(pTo, pFrom[i].getName());

					if(fromObjField == null) {
						if(!copyNulls){continue;}
						PropertyUtils.setProperty(toObj, pFrom[i].getName(), null);
						continue;
					} else {
						if( pDescCorr != null && ClassUtils.isAssignable(fromObjFieldClass, pDescCorr.getPropertyType())){//full true
							if(fromObjField instanceof BaseModel){
								if(recursiveCopy){
									fromObjModel = (BaseModel)fromObjField.getClass().newInstance();
									copyPropertiesSession((BaseModel)fromObjField, fromObjModel, true, false, true);
								}
								model = true;
								fromObjModel.setOid(null);
							}
	
							if(fromObjField instanceof List){
								fromObjList = new ArrayList<>();
								
								List col = (List)fromObjField;
								Class<?> genericClass = getGenericTypeOfListField(fromObj.getClass(), propertyName);
								Iterator it = col.iterator();
								
								if (it.hasNext()){
								    if(BaseModel.class.isAssignableFrom(genericClass)){
										for (Object o: col) {
											if(recursiveCopy){
												fromObjModel = (BaseModel)o.getClass().newInstance();
												copyPropertiesSession((BaseModel)o, fromObjModel, true, false, true);
												list = true;
												fromObjList.add(fromObjModel);
												fromObjModel.setOid(null);
											}
										}
									}
								} else { // boş list
									list = true;
								}
							}
							//OLAY BURDA
							if(model){
								PropertyUtils.setProperty(toObj, pFrom[i].getName(), fromObjModel);
								model=false;}
							else if (list){
								PropertyUtils.setProperty(toObj, pFrom[i].getName(), fromObjList);
								list=false;}
							else {
								PropertyUtils.setProperty(toObj, pFrom[i].getName(), fromObjField);}
							//
						} else {
							logger.info("\"" +pFrom[i].getName()+"\" field isimli özellik aynı diger objeye assignable değil. Manuel olarak set ediniz.");
						}
					}
				}
			} catch (Exception e) {
				throw e;
			}
		}
		return toObj;
	}
	public static boolean isManyToOne(Class<?> clazz, String fieldName){
		return isAnnotationFound(clazz, fieldName, ManyToOne.class);
	}
	public static boolean isOneToOne(Class<?> clazz, String fieldName){
		return isAnnotationFound(clazz, fieldName, OneToOne.class);
	}
	
	public static <T extends Annotation> boolean isAnnotationFound(Class<?> clazz, String fieldName, Class<T> annoClass){
		Map<String, Field> fieldsMap = getAllFields(clazz);
		for(Map.Entry<String, Field> entry : fieldsMap.entrySet()){
			Field field = entry.getValue();
			if(field.getName().equals(fieldName)){
				return field.isAnnotationPresent(annoClass);
			}
		} return false;
	}
	
	/**
	 * List olduğuna daha onceden karar verilmiş fieldlar için.
	 * @param clazz field a sahip class
	 * @param fieldName list<?> type
	 * @return
	 */
	public static Class<?> getGenericTypeOfListField(Class<?> clazz, String fieldName){
		Map<String, Field> fieldsMap = getAllFields(clazz);
		for(Map.Entry<String, Field> entry : fieldsMap.entrySet()){
			Field field = entry.getValue();
			if(field.getName().equals(fieldName)){
				Type type =  field.getGenericType();
                if (type instanceof ParameterizedType) {
                    ParameterizedType paramType = (ParameterizedType) type;
                    Type[] argTypes = paramType.getActualTypeArguments();
                    if (argTypes.length > 0) {
                        return  (Class<?>)argTypes[0];
                    }
                }
			}
		} return null;
	}
	/**
	 * 
	 * @param type HashMap<field name, field itself>
	 * @return all fields included superclass
	 */
    public static Map<String, Field> getAllFields(Class<?> type) {
        HashMap<String, Field> fieldMap = new HashMap<String, Field>();
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
        	for(Field field : c.getDeclaredFields()){
        		fieldMap.put(field.getName(), field);
        	}
        }
        return fieldMap;
    }
    
    public static String validationErrorToString(ValidationError val){
		//invalid desteklenmiyor
    	
    	//nullFields
    	StringBuilder sb = new StringBuilder("Gönderilen:"+System.getProperty("line.separator"));
    	for (String nullField : val.getNullFields()) {
			sb.append(nullField + System.getProperty("line.separator"));
		}
    	sb.append("boş olamaz");
    	
    	return sb.toString();
    }
}
