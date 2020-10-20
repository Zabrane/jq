package com.timestored.jq.ops;

import static com.timestored.jq.ops.CastOp.CAST;

import com.timestored.jdb.col.BooleanCol;
import com.timestored.jdb.col.ByteCol;
import com.timestored.jdb.col.CharacterCol;
import com.timestored.jdb.col.Col;
import com.timestored.jdb.col.ColProvider;
import com.timestored.jdb.col.ObjectCol;
import com.timestored.jdb.col.StringCol;
import com.timestored.jdb.database.CType;
import com.timestored.jq.TypeException;
import com.timestored.jq.ops.mono.CountOp;
import com.timestored.jq.ops.mono.TypeOp;

public abstract class DiadUniformBooleanBase extends BaseDiadUniformBoolean {

    public boolean ex(boolean a, boolean b) { return CastOp.CAST.b(ex(CAST.i(a), CAST.i(b))); }
    public BooleanCol ex(boolean a, BooleanCol b) { return CastOp.CAST.b(ex(CAST.i(a), CAST.i(b))); }
    public BooleanCol ex(BooleanCol a, boolean b) { return CastOp.CAST.b(ex(CAST.i(a), CAST.i(b))); }
    public BooleanCol ex(BooleanCol a, BooleanCol b) { return CastOp.CAST.b(ex(CAST.i(a), CAST.i(b))); }

	@Override public boolean ex(byte a, byte b)		{ return ex(CAST.i(a), CAST.i(b)); }
	@Override public BooleanCol ex(byte a, ByteCol b)		{ return ex(CAST.i(a), CAST.i(b)); }
	@Override public BooleanCol ex(ByteCol a, byte b)		{ return ex(CAST.i(a), CAST.i(b)); }
	@Override public BooleanCol ex(ByteCol a, ByteCol b)	{ return ex(CAST.i(a), CAST.i(b)); }


	@Override public boolean ex(char a, char b) { return ex(CAST.i(a), CAST.i(b)); }
	@Override public BooleanCol ex(char a, CharacterCol b) { return ex(CAST.i(a), CAST.i(b)); }
	@Override public BooleanCol ex(CharacterCol a, char b) { return ex(CAST.i(a), CAST.i(b)); }
	@Override public BooleanCol ex(CharacterCol a, CharacterCol b) { return ex(CAST.i(a), CAST.i(b)); }

	@Override public boolean ex(String a, String b){ throw new TypeException(); }
	@Override public BooleanCol ex(String a, StringCol b) { throw new TypeException(); }
	@Override public BooleanCol ex(StringCol a, String b){ throw new TypeException(); }
	@Override public BooleanCol ex(StringCol a, StringCol b) { throw new TypeException(); }
	
	@Override public Object ex(Object a, ObjectCol b) {
		short leftTyp = TypeOp.TYPE.type(a);
		if(leftTyp < 0 && CountOp.INSTANCE.count(b) == 0) {
			return ColProvider.emptyCol(CType.OBJECT);
		}
		throw new TypeException(); 
	}
	@Override public Object ex(ObjectCol a, Object b) { 
		short rightTyp = TypeOp.TYPE.type(b);
		if(rightTyp < 0 && CountOp.INSTANCE.count(a) == 0) {
			return ColProvider.emptyCol(CType.OBJECT);
		}
		throw new TypeException(); 
	}

	@Override public String toString() { return Op.toString(this); }
}
