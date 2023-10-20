public interface View {
    public void nextPlayer(Player player);

    public void cardPlayed(Card card, Boolean valid);

    public void roundEnd(Player roundWinner);

    public void winner(Player winner);
}
