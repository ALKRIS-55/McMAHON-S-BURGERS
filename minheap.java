class EmptyheapException extends Exception{
public EmptyheapException(String e){
    super(e);
}
}
  
public class minheap {
   public boolean qxgy(qnode x,qnode y){
      if(x.size==y.size) return x.qid>y.qid;
      else return x.size>y.size;
      
    }
    qnode [] a=new qnode[2];
    int last=0;
    public int sizeh(){
        return last;
    }
    public boolean isemptyh(){
        return (sizeh()<=0);
    }
    public qnode findmin() {
        //if(isemptyh()) throw new EmptyheapException("empty heap");
        return a[1];
    }
    public void giveindex(){
        for(int i=1;i<=last;i++){
            a[i].qind=i;
        }
    }
    public qnode deletemin() {
        // if(isemptyh()) throw new  EmptyheapException("empty heap");
        // else{
             qnode x=a[1];
             a[1]=a[last];
             last--;
           //  a[1].qind=last;
             percdown(1,a[1]);
             giveindex();
         return x;}
  //  }
    public void percdown(int i,qnode x){
         if(2*i>last){
             a[i]=x;
           //  x.qind=i;
         }
         else if(2*i==last){
             if(qxgy(x,a[2*i])) {
                 a[i]=a[2*i];
                 a[2*i]=x;
                // a[i].qind=i;
             }
         }
         else{
             int j;
             if(qxgy(a[2*i+1],a[2*i]))
                 j=2*i;
                 else j=2*i+1;
                // System.out.println(j);
                if(qxgy(x,a[j])){
                    a[i]=a[j];
                    a[j]=x;
                    if(last!=0)
                    percdown(j, x);
                }
                else a[i]=x;
             

         }
         giveindex();
    }
    public void inserth(qnode x){
         if(isemptyh()) {a[1]=x; x.qind=1; last++;}
         else{
             if(last!=a.length-1){
                 last++;
                 a[last]=x;
              //   x.qind=last;
             }
             else{
                 qnode [] b=new qnode[2*a.length];
                 for(int i=1;i<=last;i++){
                     b[i]=a[i];
                 }
                 a=b;
                 last++;
                 a[last]=x;
              //   x.qind=last;

             }
             percup(last, x);
         }
         giveindex();
    }
    public void percup(int i,qnode x){
            if(i==1) {a[i]=x;}
            else if(qxgy(a[i/2],a[i])){
                a[i]=a[i/2];
                a[i/2]=x;
                percup(i/2,x);
            }
            else {a[i]=x; x.qind=i;}
            giveindex();
    }
   public void buildminheap(qnode A[]){
       a=A;
      // System.out.println(a.length);
       last=a.length-1;
     //  System.out.println(last+"hi");
      for(int i=(last/2);i>=1;i--){
          percdown(i, a[i]);
      }
      giveindex();
   }
    
}
