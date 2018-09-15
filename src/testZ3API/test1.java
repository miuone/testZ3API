package testZ3API;

import com.microsoft.z3.*;
import java.util.*;

public class test1 {
	private static boolean useFpForReals = false;
	private static Context ctx;
    private static Solver solver;
	    
	    
	    
	public static void main(String[] args) throws Z3Exception {
		System.out.println("----start-----");
		HashMap<String, String> cfg = new HashMap<String, String>();
	    cfg.put("model", "true");
		ctx = new Context(cfg);
	    solver = ctx.mkSolver();
	    IntExpr aInt = makeIntVar("a", -10, 100);
	    IntExpr bInt = makeIntVar("b", -10, 100);
	    RealExpr cReal = makeRealVar("c", -10, 100);
	    
	    IntExpr cInt = ctx.mkReal2Int(cReal);
	    
	    
	    
	    
	    
	    BoolExpr bool1 = ctx.mkEq(aInt, cReal);
//	    ArithExpr exp1 = ctx.mkMul(new ArithExpr[] { aInt, cReal});
	    BoolExpr bool2 = ctx.mkEq(aInt, ctx.mkInt(5));
	    
	    IntExpr dInt = makeIntVar("d", 1, 100);
	    IntExpr eInt = ctx.mkInt(2);
	    BitVecExpr dBit = ctx.mkBVSHL(ctx.mkInt2BV(10, dInt), ctx.mkInt2BV(10, eInt));
	    IntExpr ddInt = ctx.mkBV2Int(dBit, false);
	    
	    BoolExpr bool3 = ctx.mkEq(dInt, ctx.mkInt(10));
	    
	    
	    
	    
	    solver.add(bool1);
	    solver.add(bool2);
	    solver.add(bool3);
	    
	    if (Status.SATISFIABLE == solver.check()) {
	    	Model m = solver.getModel();
		    System.out.println(m.eval(aInt, false) + " ... " + m.eval(bInt, false) + " ... " + m.eval(cReal, false) + " ... " + m.eval(dInt, false) + " ... " + m.eval(dBit, false) + " ... " + m.eval(ddInt, false));
		    System.out.println(m.eval(aInt, false) + " ... " + m.eval(bInt, false) + " ... " + m.eval(cReal, false) + " ... " + m.eval(dInt, false) + " ... " + m.eval(dBit, false) + " ... " + m.eval(ddInt, false));
	    } else {
	    	System.out.println("nnnnnnn");
	    }
	    
	    
	    
	    
	    Object a = 1;
	    Object b = 2;
	    Object c = a;
	    a = 100;
//	    c = 3;
//	    ((Integer)a).intValue();
	    
	    System.out.println(a + " " + b + " " + c + " " + (a==c) + " " + a.equals(c));
	    
	    System.out.println(0/10 + " " + 0%10);
	    
	    
	    
		
	}
	
	
	
	
	public static IntExpr makeIntVar(String name, long min, long max) {
	try {
	  IntExpr intConst = ctx.mkIntConst(name);
	  solver.add(ctx.mkGe(intConst, ctx.mkInt(min)));
	  solver.add(ctx.mkLe(intConst, ctx.mkInt(max)));
	  return intConst;
	} catch (Exception e) {
	  e.printStackTrace();
	  throw new RuntimeException("## Error Z3: Exception caught in makeIntVar: \n" + e);
	    }
	  }
	 public static RealExpr makeRealVar(String name, double min, double max) {
	    try {
	     
	        RealExpr expr = ctx.mkRealConst(name);
	        solver.add(ctx.mkGe(expr, ctx.mkReal("" + min)));
	        solver.add(ctx.mkLe(expr, ctx.mkReal("" + max)));
	        return expr;
	    } catch (Exception e) {
	      e.printStackTrace();
	      throw new RuntimeException("## Error Z3: Exception caught in Z3 JNI: \n" + e);
	    }
	  }

}
