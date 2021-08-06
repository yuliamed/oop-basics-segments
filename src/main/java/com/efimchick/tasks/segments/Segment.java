package com.efimchick.tasks.segments;
import static java.lang.Math.*;
public class Segment {

    private Point start;
    private Point end;
    public Segment(Point start, Point end) {
        if(start==null||end==null||(start.getX()==end.getX()&&start.getY()==end.getY())){
            throw new RuntimeException();
        }
        this.start=start;
        this.end=end;
    }

    double length() {
        return Math.sqrt((end.getX()-start.getX())*(end.getX()-start.getX())
                +(end.getY()-start.getY())*(end.getY()-start.getY()));
    }

    Point middle() {
        double x = (end.getX()+start.getX())/2;
        double y = (end.getY()+ start.getY())/2;
        Point t=new Point(x,y);
        return t;
    }

    Point intersection(Segment another) {
        double b1=(this.end.getY()-this.start.getY())/(this.end.getX()-this.start.getX());
        double a1 = this.end.getY()-b1*this.end.getX();

        double b2=(another.end.getY()-another.start.getY())/(another.end.getX()-another.start.getX());
        double a2 = another.end.getY()-b2*another.end.getX();
        if(b2==b1 )
            if( a1!=a2) return null;
            else {
                if(this.start.getX()==another.end.getX()) {
                    if((this.start.getX()>this.end.getX()&&this.start.getX()<another.start.getX())||
                            (this.start.getX()<this.end.getX()&&this.start.getX()>another.start.getX())) return this.start;}
                else if(this.start.getX()==another.start.getX()){
                    if((this.start.getX()>this.end.getX()&&this.start.getX()<another.end.getX())||
                            (this.start.getX()<this.end.getX()&&this.start.getX()>another.end.getX())) return this.start;}
                else if(this.end.getX()==another.start.getX()){
                    if((this.end.getX()>this.start.getX()&&this.end.getX()<another.end.getX())||
                            (this.end.getX()<this.start.getX()&&this.end.getX()>another.end.getX())) return this.end;}
                else if(this.end.getX()==another.end.getX()){
                    if((this.end.getX()>this.start.getX()&&this.end.getX()<another.start.getX())||
                            (this.end.getX()<this.start.getX()&&this.end.getX()>another.start.getX())) return this.end;}
                return null;
            }


        double x = (a1-a2)/(b2-b1);
        double y = a1+b1*x;
        Point t=new Point(x,y);
        if(isSegmentContainsPoint(t,another)&&isSegmentContainsPoint(t,this)){

            return t;
        }
        return null;
    }

    private boolean isSegmentContainsPoint(Point p, Segment s) {
        if(s.start.getX()<s.end.getX()){
            if(s.start.getX()>p.getX()||s.end.getX()<p.getX()) return false;}
        else if(s.start.getX()<p.getX()||s.end.getX()>p.getX()) return false;
        if(s.start.getY()<s.end.getY()){
            if(s.start.getY()>p.getY()||s.end.getY()<p.getY()) return false;}
        else if(s.start.getY()<p.getY()||s.end.getY()>p.getY()) return false;
        return true;
    }

}
