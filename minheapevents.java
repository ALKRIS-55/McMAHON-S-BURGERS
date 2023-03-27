

public class minheapevents {
    boolean xgy(events x,events y){
        if(x.t==y.t){
          if(x.priority==1&&y.priority==1){
              return x.c.qno<y.c.qno;
          }
          if(x.priority==2&&y.priority==2){
            return x.c.depcountertime>y.c.depcountertime;
        }
        if(x.priority==3&&y.priority==3){
            return x.c.depcountertime>y.c.depcountertime;
        }
          else return x.priority>y.priority;
        }
        else return x.t>y.t;
        
    }
    //public class minheap {
        events [] a=new events[2];
        int last=0;
        public int sizeh(){
            return last;
        }
        public boolean isemptyh(){
            return sizeh()<=0;
        }
        public events findmin() {
            //if(isemptyh()) throw new EmptyheapException("empty heap");
            return a[1];
        }
        public events deletemin() {
            // if(isemptyh()) throw new  EmptyheapException("empty heap");
            // else{
                 events x=a[1];
                // System.out.println(last);
                 a[1]=a[last];
                 last--;
                 if(last!=0)
                 percdown(1,a[1]);
             return x;}
      //  }
        public void percdown(int i,events x){
             if(2*i>last){
                 a[i]=x;
             }
             else if(2*i==last){
                 if(xgy(x,a[2*i])) {
                     a[i]=a[2*i];
                     a[2*i]=x;
                 }
             }
             else{
                 int j;
                 if(xgy(a[2*i+1],a[2*i]))
                     j=2*i;
                     else j=2*i+1;
                    // System.out.println(j);
                    if(xgy(x,a[j])){
                        a[i]=a[j];
                        a[j]=x;
                        percdown(j, x);
                    }
                    else a[i]=x;
                 
    
             }
    
        }
        public void inserth(events x){
             if(isemptyh()) {a[1]=x; last++;}
             else{
                 if(last!=a.length-1){
                     last++;
                     a[last]=x;
                 }
                 else{
                     events [] b=new events[2*a.length];
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
        public void percup(int i,events x){
                if(i==1) {a[i]=x;}
                else if(xgy(a[i/2],a[i])){
                    a[i]=a[i/2];
                    a[i/2]=x;
                    percup(i/2,x);
                }
                else {a[i]=x;}
        }
       public void buildminheap(events A[]){
           a=A;
          // System.out.println(a.length);
           last=a.length-1;
         //  System.out.println(last+"hi");
          for(int i=(last/2);i>=1;i--){
              percdown(i, a[i]);
          }
       }
       /*public static void main(String[] args){
           minheapevents eve=new minheapevents();
           events x=new events();
           Customer c=new Customer();
           c.qno=2;
           x.t=2;
           x.priority=1;
           x.c=c;
           eve.inserth(x);
           events y=new events();
           Customer d=new Customer();
           d.qno=1;
           y.t=12;
           y.priority=2;
           y.c=d;
           eve.inserth(y);
           events w=new events();
           Customer f=new Customer();
           f.qno=2;
           w.t=1;
           w.priority=1;
           w.c=f;
           eve.inserth(w);
           events z=new events();
           Customer e=new Customer();
           e.qno=1;
           z.t=1;
           z.priority=1;
           z.c=e;
           eve.inserth(z);
           System.out.println(eve.findmin().c.qno);
       }*/
    }
    

