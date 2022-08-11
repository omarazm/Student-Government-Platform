/*
Omar Abouelazm
Period: 3

This class is used for creating a poll answer object to store a 
user's answer and the number of votes a poll has
 */
public class PollAnswer
{
    private String answer;
    private int voteNumber;
    
    PollAnswer()
    {
        answer = "";
        voteNumber = 0;
    }
    PollAnswer(String pAnswer, int pVoteNumber)
    {
        answer = pAnswer;
        voteNumber = pVoteNumber;
    }
    //sets info
    public void setAnswer(String pAnswer)
    {
        this.answer = pAnswer;
    }
    public void setVoteNumber(int pVoteNumber)
    {
        this.voteNumber = pVoteNumber;
    }
    //gets info
    public String getAnswer()
    {
        return this.answer;
    }
    public int getVoteNumber()
    {
        return this.voteNumber;
    }
    
    public static void main(String[] args)
    {
        PollAnswer pollAnswerObj = new PollAnswer("answer", 2);
        
        System.out.println(pollAnswerObj.getAnswer() + pollAnswerObj.getVoteNumber());
    }
}
