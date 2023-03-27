class Emptyqueueexception extends Exception{
    public Emptyqueueexception(String e){
        super(e);
    }
}
public class queue {
    Customer [] a=new Customer[1];
    int f=0,r=0,N=1;
    public int sizeq(){
        return (N+r-f)%N;
    }
    public boolean isemptyq(){
        return (f==r);
    }
    public Customer front(){
        return a[f];
    }
public void enqueue(Customer n){
    if(sizeq()!=N-1) {
      a[r]=n;
      r=(r+1)%N;
    }
    else{
        Customer [] b=new Customer[2*N];
        int j=f;
        for(int i=0;i<N-1;i++){
            b[i]=a[j];
            j=(j+1)%N;  
        }
        a=b;
        f=0; 
        r=N-1;
        N=a.length;
        a[r]=n;
       // System.out.println(a[r].id);
        r=(r+1)%N;
    }
}
public Customer dequeue(Customer o) {
//if(isemptyq()) throw new Emptyqueueexception("empty queue");
//else{
    a[f]=o;
    f=(f+1)%N;
return o;
//}
}
}
