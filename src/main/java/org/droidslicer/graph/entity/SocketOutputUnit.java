package org.droidslicer.graph.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.ibm.wala.ipa.callgraph.CGNode;
import com.ibm.wala.ipa.slicer.Statement;
import com.ibm.wala.ssa.IR;
import com.ibm.wala.ssa.SSAInstruction;

public class SocketOutputUnit extends SocketUnit
	implements IInstructionUnit, IStatementFlowUnit, IMutableStatementInflowUnit
{
	private final Set<Statement> mInflowStms = new HashSet<Statement>();
	private final CGNode mNode;
	private final int mInstIdx;
	public SocketOutputUnit(CGNode node, int instIdx)
	{
		mNode = node;
		mInstIdx = instIdx;
	}
	@Override
	public void addInflowStatement(Statement stm)
	{
		mInflowStms.add(stm);
	}

	@Override
	public boolean isAllowFlowThrough()
	{
		return false;
	}

	@Override
	public Collection<Statement> getInflowStatements() 
	{
		return mInflowStms;
	}

	@Override
	public Collection<Statement> getOutflowStatements()
	{
		return Collections.emptySet();
	}

	@Override
	public CGNode getNode() 
	{
		return mNode;
	}

	@Override
	public int getInstructionIndex() 
	{
		return mInstIdx;
	}
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append('[');
		builder.append(SocketOutputUnit.class.getSimpleName());
		builder.append(" value=");
		builder.append(getSocketValue());
		builder.append(", node=[");
		builder.append(mNode);
		builder.append(']');
		{
			IR ir = mNode.getIR();
			if(ir != null)
			{
				SSAInstruction inst = ir.getInstructions()[mInstIdx];
				builder.append(", inst=[");
				builder.append(inst);
				builder.append(']');
			}
		}
		builder.append(']');
		return builder.toString();
	}
	@Override
	public boolean visit(IEntityVisitor visitor)
	{
		if(visitor.visitSocketOutputUnit(this))
			return true;
		else
			return super.visit(visitor);
	}
}
