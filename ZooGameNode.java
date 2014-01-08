
/**
 * ZooGameNode is required for the tree data structures in ZooGame.java 
 * @author Joshua Michael Waggoner
 * 
 */
public class ZooGameNode
{

	ZooGameNode yes;
	ZooGameNode no;
	String data;

	ZooGameNode(String data, ZooGameNode yes, ZooGameNode no)
	{
		this.yes = yes;
		this.no = no;
		this.data = data;
	}

	public void setData(String data)
	{
		this.data = data;
	}

	public String getData()
	{
		return this.data;
	}

	// Yes is treated as left
	public void setYesReference(ZooGameNode yes)
	{
		this.yes = yes;
	}

	// No is treated as right
	public void setNoReference(ZooGameNode no)
	{
		this.no = no;
	}

	public ZooGameNode getYesReference()
	{
		return this.yes;
	}

	public ZooGameNode getNoReference()
	{
		return this.no;
	}

}
