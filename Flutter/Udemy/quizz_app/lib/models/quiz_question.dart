class QuizQuestion {
  final String question;
  final List<String> answers;

  const QuizQuestion(this.question, this.answers);

  List<String> getshuffeledAnswers() {
    final shuffeledList = List.of(answers);
    shuffeledList.shuffle();
    return shuffeledList;
  }
}
