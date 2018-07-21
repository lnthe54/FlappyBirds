
package gui;

public class QueueList <FlappyBird> {
    
    private Element head, foot;
    
    private int size = 0;
    
    public QueueList(){
        head = foot = null;
    }
    
    public int getSize(){
        return size;
    }
    
    public void push(FlappyBird flappyBird){
        
        Element e = new Element(flappyBird);
        
        if(head == null){
            head = foot = e;
        }else{
            foot.next = e;
            foot = e;
        }
        size++;
    }
    
    public FlappyBird pop(){
        FlappyBird value = head.value;
        head = head.next;
        size--;
        return value;
    }
    
    public FlappyBird get(int id){
        
        Element e = head;
        
        if(head == null) return null;
        
        for(int i = 0;i < id; i++){
            e = e.next;
            if(e == null) return null;
        }
        
        return e.value;
    }
    
    private class Element{
        
        Element(FlappyBird value){
            this.value = value;
        }
        
        FlappyBird value;
        Element next;
    }
}
