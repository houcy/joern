package inputModules.csv.csv2ast;

import ast.ASTNode;
import inputModules.csv.KeyedCSV.KeyedCSVRow;

public class CSVASTNodeFactory
{
	private NodeTypeMapper nodeMapper;

	public CSVASTNodeFactory(NodeTypeMapper nodeMapper)
	{
		this.nodeMapper = nodeMapper;
	}

	public ASTNode createNode(KeyedCSVRow keyedRow)
	{
		String nodeType = keyedRow.getFieldForKey("type");
		nodeType = nodeMapper.map(nodeType);
		ASTNode newNode;

		try
		{
			newNode = (ASTNode) Class.forName(nodeType).newInstance();
			// TODO: If we change ASTNode to store String-properties at core,
			// we can initialize it using `keyedRow` here.
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e)
		{
			return null;
		}

		return newNode;
	}

}
