public class maxheap {
    qnode [] a=new qnode[2];
    int last=0;
    public int sizeh(){
        return last;
    }
    public boolean isemptyh(){
        return sizeh()>0;
    }
    public qnode findmax() throws  EmptyheapException{
        if(isemptyh()) throw new EmptyheapException("empty heap");
        return a[1];
    }
    public qnode deletemax() throws EmptyheapException{
         if(isemptyh()) throw new  EmptyheapException("empty heap");
         else{
             qnode x=a[1];
             a[1]=a[last];
             last--;
             percdown(1,a[1]);
         return x;}
    }
    public void percdown(int i,qnode x){
         if(2*i>last){
             a[i]=x;
         }
         else if(2*i==last){
             if(a[2*i].size>x.size) {
                 a[i]=a[2*i];
                 a[2*i]=x;
             }
         }
         else{
             int j;
             if(a[2*i].size>a[2*i+1].size)
                 j=2*i;
                 else j=2*i+1;
                if(a[j].size>x.size){
                    a[i]=a[j];
                    percdown(j, x);
                }
                else a[i]=x;
             

         }

    }
    public void inserth(qnode x){
         if(isemptyh()) {a[1]=x; last++;}
         else{
             if(last!=a.length-1){
                 a[last++]=x;
             }
             else{
                 qnode [] b=new qnode[2*a.length];
                 for(int i=1;i<=last;i++){
                     b[i]=a[i];
                 }
                 a=b;
                 a[last++]=x;

             }
             percup(last, x);
         }
    }
    public void percup(int i,qnode x){
            if(i==1) {a[i]=x;}
            else if(a[i].size>a[i/2].size){
                a[i]=a[i/2];
                percup(i/2,x);
            }
            else {a[i]=x;}
    }
    public void buildminheap(qnode a[]){
        for(int i=(last/2);i>=1;i++){
            percdown(i, a[i]);
        }
     }
}
