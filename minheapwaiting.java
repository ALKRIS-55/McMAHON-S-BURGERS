public class minheapwaiting {
    public boolean cxgy(Customer x,Customer y){
        if(x.depcountertime==y.depcountertime) return x.qno<y.qno;
        else return x.depcountertime>y.depcountertime;
        
      }
      Customer [] a=new Customer[2];
      int last=0;
      public int sizeh(){
          return last;
      }
      public boolean isemptyh(){
          return sizeh()<=0;
      }
      public Customer findmin() {
          //if(isemptyh()) throw new EmptyheapException("empty heap");
          return a[1];
      }
      public Customer deletemin() {
          // if(isemptyh()) throw new  EmptyheapException("empty heap");
          // else{
               Customer x=a[1];
               a[1]=a[last];
               last--;
               if(last!=0)
               percdown(1,a[1]);
           return x;}
    //  }
      public void percdown(int i,Customer x){
           if(2*i>last){
               a[i]=x;
           }
           else if(2*i==last){
               if(cxgy(x,a[2*i])) {
                   a[i]=a[2*i];
                   a[2*i]=x;
               }
           }
           else{
               int j;
               if(cxgy(a[2*i+1],a[2*i]))
                   j=2*i;
                   else j=2*i+1;
                  // System.out.println(j);
                  if(cxgy(x,a[j])){
                      a[i]=a[j];
                      a[j]=x;
                      percdown(j, x);
                  }
                  else a[i]=x;
               
  
           }
  
      }
      public void inserth(Customer x){
           if(isemptyh()) {a[1]=x; last++;}
           else{
               if(last!=a.length-1){
                   last++;
                   a[last]=x;
               }
               else{
                   Customer [] b=new Customer[2*a.length];
                   for(int i=1;i<=last;i++){
                       b[i]=a[i];
                   }
                   a=b;
                   last++;
                   a[last]=x;
  
               }
               percup(last, x);
           }
      }
      public void percup(int i,Customer x){
              if(i==1) {a[i]=x;}
              else if(cxgy(a[i/2],a[i])){
                  a[i]=a[i/2];
                  a[i/2]=x;
                  percup(i/2,x);
              }
              else {a[i]=x;}
      }
     public void buildminheap(Customer A[]){
         a=A;
        // System.out.println(a.length);
         last=a.length-1;
       //  System.out.println(last+"hi");
        for(int i=(last/2);i>=1;i--){
            percdown(i, a[i]);
        }
     }
}
