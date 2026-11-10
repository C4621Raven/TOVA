public class ReactionInstance
{
	public int block;
	public int subSection;
	public boolean target;
	
	public long reaction;
	
	public ReactionInstance(int block, int subSection, boolean target, long reaction)
	{
		this.block = block;
		this.subSection = subSection;
		this.target = target;
		this.reaction = reaction;
	}
	
	public String twoString()
	{
		return "block: " + block + ", subSection: " + subSection + ", target: " + target + ", reaction: " + reaction;
	}
}