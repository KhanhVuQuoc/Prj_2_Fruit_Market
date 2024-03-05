/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTransfer;

/**
 *
 * @author x
 */
public class ObjectTransfer {
    private static ObjectTransfer instancesObjectTransfer;
    private static Object dataTransfer;
    private ObjectTransfer(){
        
    }
    
    public static ObjectTransfer getInstance(){
        if(instancesObjectTransfer == null){
            instancesObjectTransfer = new ObjectTransfer();
        }
        return instancesObjectTransfer;
    }
    
    public Object getData(){
        return dataTransfer;
    }
    public void setData(Object dataTransObject){
        dataTransfer = dataTransObject;
    }
    public void removeData(){
        dataTransfer = null;
    }
    
}
