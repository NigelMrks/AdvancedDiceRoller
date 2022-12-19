import kotlin.system.exitProcess

var playerOne = "Player 1"
var playerTwo = "Player 2"
var winsPlayerOne = 0
var winsPlayerTwo = 0
var currentDiceType = 6

fun main(args: Array<String>) {
    start()
}
fun start() {
    var options: List<String> = listOf(
        "Change Player Names", "Start New Game", "Change Dice Type", "End Program"
    )
    println("Was möchten sie tun?")
    var iOptions = 0
    while (iOptions < options.size) {
        println("Für ${options[iOptions]} bitte ${iOptions+1} eingeben.")
        iOptions++
    }
    var optionsChoice: Int = -1
    try {
        optionsChoice = readln().toInt()
    }
    catch (ex: Exception) {
        println("Ihre Eingabe war ungültig.")
        println("Versuchen sie es erneut und geben eine Zahl zwischen 1 und ${options.size} ein.")
    }
    if (optionsChoice >= 1 && optionsChoice <= options.size) {
        when (optionsChoice) {
            1 -> changeNames()
            2 -> newGame()
            3 -> changeDice()
            4 -> shutDown()
        }
    }
    else {
        println("Ihre Eingabe war ungültig.")
        println("Versuchen sie es erneut und geben eine Zahl zwischen 1 und ${options.size} ein.")
        start()
    }
}
fun changeNames() {
    println("Bitte einen neuen Namen für Spieler 1 ($playerOne) eingeben.")
    playerOne = readln()
    println("Spieler 1 ist nun $playerOne.")
    println("Bitte einen neuen Namen für Spieler 2 ($playerTwo) eingeben.")
    playerTwo = readln()
    println("Spieler 2 ist nun $playerTwo.")
    winsPlayerOne = 0
    winsPlayerTwo = 0
    println("Scoreboard wurde zurückgesetzt.")
    start()
}
fun newGame() {
    println("Es beginnt ein neues Spiel zwischen $playerOne und $playerTwo.")
    println("Benutzter Würfel: D$currentDiceType")
    println("$playerOne hat $winsPlayerOne mal gewonnen.")
    println("$playerTwo hat $winsPlayerTwo mal gewonnen.")
    println("Neues Spiel beginnt.")
    var rollOne = rolling()
    var rollTwo = rolling()
    println("$playerOne hate eine $rollOne gerollt.")
    println("$playerTwo hate eine $rollTwo gerollt.")

    if (rollOne > rollTwo) {
        winsPlayerOne++
        println("$playerOne hat gewonnen.")
        println("Dies ist der $winsPlayerOne. Sieg von $playerOne.")
    }
    else if (rollOne < rollTwo) {
        winsPlayerTwo++
        println("$playerTwo hat gewonnen.")
        println("Dies ist der $winsPlayerTwo. Sieg von $playerTwo.")
    }
    else {
        println("Dieser Wurf war ein unentschieden!")
    }

    if (winsPlayerOne > winsPlayerTwo) {
        println("$playerOne ist in Führung mit ${winsPlayerOne-winsPlayerTwo} Sieg(en).")
    }
    else if (winsPlayerOne < winsPlayerTwo) {
        println("$playerTwo ist in Führung mit ${winsPlayerTwo-winsPlayerOne} Sieg(en).")
    }
    else {
        println("Damit steht es jetzt unentschieden zwischen $playerOne und $playerTwo ($winsPlayerOne Sieg(e))")
    }
    start()

}
fun rolling(): Int {
    var roll = (1..currentDiceType).random()
    return roll
}
fun changeDice() {
    var diceOptions: List<Int> = listOf(4, 6, 8, 10, 12, 20, 100)
    println("Sie benutzen momentan einen D$currentDiceType für das Spiel.")
    println("Sie haben folgende Optionen: D4, D6, D8, D12, D20, D100")
    println("Bitte geben sie ein wieviele Seiten der Würfel haben soll.")
    var diceChoice = -1
    try {
        diceChoice = readln().toInt()
    }
    catch (ex:Exception) {
        println("Ihre Eingabe war ungültig.")
        println("Bitte geben sie eine der folgenden Zahlen ein: 4, 6, 8, 10, 12, 20, 100")
        changeDice()
    }
    if (diceOptions.contains(diceChoice)) {
        when (diceChoice) {
            4 -> currentDiceType = 4
            6 -> currentDiceType = 6
            8 -> currentDiceType = 8
            10 -> currentDiceType = 10
            12 -> currentDiceType = 12
            20 -> currentDiceType = 20
            100 -> currentDiceType = 100
        }
        println("Ihr Spiel nutzt nun einen D$currentDiceType")
        println("Kehre zum Hauptmenü zurück..")
        start()
    }
    else {
        println("Es gibt leider keinen Würfel mit dieser Seitenanzahl.")
        println("Kehre zum Hauptmenü zurück..")
        start()
    }
}
fun shutDown() {
    println("Programm wir beendet. Danke fürs Spielen!")
    exitProcess(0)
}