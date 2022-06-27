public class Data 
{
	private int data;

	public Data () 
	{
		this ( 0 );
	}
	
	public Data ( int value ) 
	{
		setData ( value );
	}

	public void setData ( int item ) 
	{
		data = ( item >= 0 ) ? item : 0;
	}

	public int getData ( ) 
	{
		return data;
	}

}
