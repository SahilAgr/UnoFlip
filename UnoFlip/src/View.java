public interface View {
    public void nextPlayer(Player player);

    public void cardPlayed(Card card);

    public void roundEnd(Player roundWinner);

    public void winner(Player winner);
}
