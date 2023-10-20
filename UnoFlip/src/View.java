public interface View {
    public void nextPlayer();

    public void cardPlayed();

    public void roundEnd(Player roundWinner);

    public void winner(Player winner);
}
