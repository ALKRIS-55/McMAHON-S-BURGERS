

class Customer{
    int id,qno,arrivetime,departtime=0,depcountertime,state=0,burgers,leftburgers;
    int ht;
    Customer left,right;
    queue que;
}
class qnode{
    int size=0;
    int qid;
     queue q=new queue();
     int qind;
}
class events{
    int t=0,priority=0;
    Customer c;
    int prevburgers=0;
    Object info;
}
public class MMBurgers implements MMBurgersInterface {
    int M=0,K=0,t=0,prevt=0,currm=0,griddlewaiting=0,delwait=0;
    int previd=0;
    int totalcustomers=0,totalcustwaittime=0;
    minheapwaiting qwait=new minheapwaiting();
    
    minheap h1=new minheap();
    Customer [] Custlist=new Customer[100];
   // Customer AVLR=new Customer();
   // AVL T = new AVL();
    qnode [] B=new qnode[1];
   // minheap Events=new minheap();
    minheapevents E=new minheapevents();
    public boolean isEmpty(){
         {if(E.sizeh()==0)
            return true;}
       
        //your implementation
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
        return false;
    } 
    
    public void setK(int k) throws IllegalNumberException{
        if(k<=0) throw new IllegalNumberException("invalid k");
        else{
            K=k;
            qnode [] A=new qnode[K+1];
            for(int i=1;i<=K;i++){
                qnode t=new qnode();
               // if(i==1) t.size=1;
                t.qid=i;
                h1.inserth(t);
                A[i]=t;
                // A[i].qid=i;
              //  System.out.println(h1.sizeh()); 
             //   System.out.println(h1.findmin().qid);
            }
         //   System.out.println(h1.sizeh());
         //   System.out.println(h1.findmin().qid);
            B=A;
           // System.out.println(A.length);
           // h1.buildminheap(B);
          //  for(int i=1;i<=K;i++) System.out.println(A[i].qid);

        } 
      //  System.out.println(qwait.sizeh());
        //your implementation
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    }   
    
    public void setM(int m) throws IllegalNumberException{
        if(m<=0) throw new IllegalNumberException("invalid k");
        else{
            M=m;
        } 
        //your implementation
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    } 

    public void advanceTime(int t) throws IllegalNumberException{
      if(t<0) throw new IllegalNumberException("");
        if(t<prevt) return;
        prevt=t;
        if(E.sizeh()==0) return;
     //   System.out.println("E not empty");
        events e=E.findmin();
        if(e==null) return;
        while (e!=null&&e.t<=t){
        if(e.priority==1){
         //   System.out.println(e.c.id+"hi"+"p="+e.priority);
            E.deletemin();
            B[e.c.qno].q.dequeue(e.c);
            B[e.c.qno].size--;
            h1.percup(B[e.c.qno].qind, B[e.c.qno]);
           // System.out.println(h1.findmin().size);
            //h1.percup(i, x);
           // qwait.enqueue(e.c);
            if(qwait.sizeh()==0){
            if(currm<M){
                if(M-currm>=e.c.burgers){
                    currm+=e.c.burgers;
                    e.prevburgers=e.c.burgers;
                    e.c.leftburgers=0;
                    events r=new events();
                    r.t=e.t+10;
                    r.priority=2;
                    r.prevburgers=e.c.burgers;
                    r.c=e.c;
                    E.inserth(r);
                }
                else{
                    qwait.inserth(e.c);
                  //  System.out.println(e.c.id+"inserted in queue at"+e.t+" "+e.c.leftburgers);
                    e.c.leftburgers=e.c.burgers-(M-currm);
                  //  System.out.println(e.c.id+"inserted in queue at"+e.t+" "+e.c.leftburgers);
                    int prevburgers=M-currm;
                    griddlewaiting+=e.c.leftburgers;
                    currm=M;
                    events r=new events();
                    r.t=e.t+10;
                    r.priority=3;
                    r.prevburgers=prevburgers;
                    r.c=e.c;
                    E.inserth(r);
                   
                }
            }
            else{
               // System.out.println(e.c.id+"inserted in queue at"+e.t+" "+e.c.leftburgers+" qsize=0");
                qwait.inserth(e.c);
                griddlewaiting+=e.c.burgers;
            }
        }
        else{
          //  System.out.println(qwait.sizeh()+"qwait size");
          //  System.out.println(e.c.id+"inserted in queue at"+e.t+" "+e.c.leftburgers+"no pan space");
            qwait.inserth(e.c);
            griddlewaiting+=e.c.burgers;
        }
        }
        else if(e.priority==2){
          //  System.out.println(e.c.id+"hi"+"p="+e.priority);
            E.deletemin();
             //qwait.deletemin();
          //  System.out.println(currm+" hi");
             currm-=e.prevburgers;
          //  System.out.println(currm+" hi1");
             events r=new events();
             r.t=e.t+1;
             r.priority=5;
             r.c=e.c;
             E.inserth(r);
         if(E.sizeh()==0) return;
         events s=E.findmin();
         
         while((s.priority==2||s.priority==3)&&(s.t==e.t)){
           //  System.out.println(s.c.id+" same time");
            E.deletemin();
               currm-=s.prevburgers;
              //System.out.println(currm+" hi");
               if(s.priority==2){
                   events z=new events();
                   z.t=e.t+1;
                   z.priority=5;
                   z.c=s.c;
                   E.inserth(z);
               }
              // E.deletemin();
               if(E.sizeh()==0) return;
               s=E.findmin();
                    }
                    if(currm<M){
        while(qwait.sizeh()!=0){
            Customer c=qwait.findmin();
         //   System.out.println(c.id+" qmin");
            if(currm<M){
                if(M-currm>=c.leftburgers){
                    qwait.deletemin();
                    currm+=c.leftburgers;
                    int prevburgers=c.leftburgers;
                    griddlewaiting-=c.leftburgers;
                    c.leftburgers=0;
            //        System.out.println(c.id+" inserted in E for p=2 at"+e.t+" "+c.leftburgers+" "+currm);
                    events l=new events();
                    l.t=e.t+10;
                    l.priority=2;
                    l.prevburgers=prevburgers;
                    l.c=c;
                    E.inserth(l);
                }
                else{
                  //  qwait.inserth(e.c);
                  if(currm<M){
                    c.leftburgers-=(M-currm);
                    int prevburgers=M-currm;
                    griddlewaiting-=M-currm;
                    currm=M;
                 //   System.out.println(c.id+" inserted in E for p=3 at"+e.t+" "+c.leftburgers);
                    events l=new events();
                    l.t=e.t+10;
                    l.priority=3;
                    l.prevburgers=prevburgers;
                    l.c=c;
                    E.inserth(l);
                  }
                }
            }
            if(currm==M) break;
        } 
    }
        }
        else if(e.priority==3){
           // System.out.println(e.c.id+"hi"+"p="+e.priority);
           // qwait.deletemin();
          E.deletemin();
             currm-=e.prevburgers;
           //  System.out.println(currm);
            /* events r=new events();
             r.t=e.t+1;
             r.priority=5;
             E.inserth(r);*/
         if(E.sizeh()==0) return;
         events s=E.findmin();
        // System.out.println("Hiiicheck");
         while((s.priority==2||s.priority==3)&&(s.t==e.t)){
               currm-=s.prevburgers;
               if(s.priority==2){
                   events z=new events();
                   z.t=e.t+1;
                   z.priority=5;
                   z.c=s.c;
                   E.inserth(z);
               }
               E.deletemin();
               if(E.sizeh()==0) return;
               s=E.findmin();
                    }
                    if(currm<M){
        while(qwait.sizeh()!=0){
            Customer c=qwait.findmin();
          //  if(c.leftburgers==0) continue;
           // System.out.println(c.id+" kkk");
            if(currm<M){
                if(M-currm>=c.leftburgers){
                    qwait.deletemin();
                   
                    currm+=c.leftburgers;
                    int prevburgers=c.leftburgers;
                    griddlewaiting-=c.leftburgers;
                    c.leftburgers=0;
                 //   System.out.println(c.id+"insertd in E for p=2 at"+e.t+" "+c.leftburgers);
                  //  griddlewaiting-=c.leftburgers;
                    events l=new events();
                    l.t=e.t+10;
                    l.priority=2;
                    l.prevburgers=prevburgers;
                    l.c=c;
                    E.inserth(l);
                }
                else{
                    if(currm!=M){
                   // qwait.inserth(e.c);
                   
                    c.leftburgers-=(M-currm);
                    int prevburgers=M-currm;
                    griddlewaiting-=M-currm;
                    currm=M;
                 //   System.out.println(c.id+"insertd in E for p=3 at"+e.t+" "+c.leftburgers);
                    events l=new events();
                    l.t=e.t+10;
                    l.priority=3;
                    l.prevburgers=prevburgers;
                    l.c=c;
                    E.inserth(l);
                    }
                }
            }
            if(currm==M) break;
        } 
    }
        }
        else if(e.priority==4){
          //  System.out.println(e.c.id+"hi"+"p="+e.priority+" "+e.t);
            E.deletemin();
         //   System.out.println(E.sizeh());
            qnode w=h1.findmin();
            e.c.qno=w.qid;
            e.c.que=w.q;
            //System.out.println(w.qid);
        //  System.out.println(e.c.qno+" "+w.qid);
            h1.findmin().q.enqueue(e.c);
           // h1.findmin().size++;
           // System.out.println(h1.findmin().qid);
           // System.out.println(h1.findmin().q.front().id);
           h1.findmin().size++;
            if(h1.findmin().q.front()==e.c){
                e.c.depcountertime=t+e.c.qno;
            }
            
            else e.c.depcountertime=h1.findmin().q.front().depcountertime+(h1.findmin().size-1)*e.c.qno;
          //  System.out.println(+e.c.depcountertime+" "+e.c.qno);
           // h1.findmin().size++;
            h1.percdown(1,h1.findmin());
          //  System.out.println(h1.findmin().qid);
           e. c.state=e.c.qno;
           events r=new events();
           r.t=e.c.depcountertime;
           r.priority=1;
           r.c=e.c;
           E.inserth(r);
        }
        else if(e.priority==5){
           // System.out.println(e.c.id+"hi"+"p="+e.priority);
            E.deletemin();
           // System.out.println(e.c.id);
             e.c.departtime=e.t;
            totalcustwaittime+=e.c.departtime-e.c.arrivetime;
            totalcustomers++;
           // System.out.println(totalcustomers);
         //  System.out.println(e.c.arrivetime+" "+e.c.departtime);
        }
        if(E.sizeh()==0) break;
        e=E.findmin();
        if(e==null) break;
    }
        //your implementation
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    } 

    public void arriveCustomer(int id, int t, int numb) throws IllegalNumberException{
        if(id<=0||t<prevt||numb<1||id<=previd) throw new IllegalNumberException("invalid customer");
        else{
          prevt=t;
            Customer c= new Customer();
            c.id=id;
            c.arrivetime=t;
            c.burgers=numb;
            c.leftburgers=numb;
            if(id>=Custlist.length){
              Customer []arr=new Customer[2*Custlist.length];
              for(int i=1;i<Custlist.length;i++){
                arr[i]=Custlist[i];
              }
               Custlist=arr;
            }
            Custlist[id]=c;
            previd=id;
           // AVLR=T.insert(AVLR,id,c);
            events x=new events();
            x.t=t;
            x.priority=4;
            x.c=c;
            E.inserth(x);
         //   System.out.println(E.sizeh());
           /* qnode w=h1.findmin();
            c.qno=w.qid;
            c.que=w.q;
          // System.out.println(c.qno);
            h1.findmin().q.enqueue(c);
           // System.out.println(h1.findmin().qid);
           // System.out.println(h1.findmin().q.front().id);
            if(h1.findmin().q.front()==c){
                c.depcountertime=t+c.qno;
            }
            else c.depcountertime=t+h1.findmin().q.front().departtime+h1.findmin().q.sizeq()*c.qno;
            h1.findmin().size++;
            h1.percdown(1,h1.findmin());
            c.state=c.qno;
          //  System.out.println(c.state);
           
            //catch(Exception e){*/
             //   System.out.println(E.findmin().c.id+" "+E.findmin().priority+" "+E.findmin().t);
             advanceTime(t);   
          // System.out.println(c.depcountertime);
            


        }
    
        //your implementation
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    } 

    public int customerState(int id, int t) throws IllegalNumberException{
        if(id<=0||t<prevt) throw new IllegalNumberException("invalid id");
       if( id>previd) return 0;
        //your implementation
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
      prevt=t;
        advanceTime(t);	
        Customer v=Custlist[id];
       // System.out.println(v.id+" "+v.depcountertime);
        if(t<v.depcountertime) return v.qno;
        if(v.departtime!=0&&t>=v.departtime) {
           // System.out.println("hii");
            return K+2;}
        if(t>=v.depcountertime) return K+1;
       // if(t>=v.depcountertime&&t<v.departtime) return K;
        
        return 0;
    } 

    public int griddleState(int t) throws IllegalNumberException{
        if(t<prevt) throw new IllegalNumberException("");
        //your implementation
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
      prevt=t;
        advanceTime(t);
        return currm;
    } 

    public int griddleWait(int t) throws IllegalNumberException{
        //your implementation
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
      if(t<0) throw new IllegalNumberException("");
      prevt=t;
        advanceTime(t);
        return griddlewaiting;
    } 

    public int customerWaitTime(int id) throws IllegalNumberException{
        //your implementation
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
       // System.out.println("hi");	
      // System.out.println(Custlist[id].depcountertime);
     //  System.err.println(Custlist[id].departtime+" "+Custlist[id].depcountertime+" "+ Custlist[id].arrivetime);
      //  System.out.println( (Custlist[id].departtime- Custlist[id].arrivetime));
      if(id<0) throw new IllegalNumberException("");
      prevt=t;
        return (Custlist[id].departtime- Custlist[id].arrivetime);
    } 

	public float avgWaitTime(){
        //your implementation
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
        float x=(float)totalcustwaittime;
        float y=(float)totalcustomers;
     //   System.out.println(x);
     //   System.out.println(totalcustomers);
        float z=x/y;
      //  System.out.println(z);
        return z;
    } 

    
}
