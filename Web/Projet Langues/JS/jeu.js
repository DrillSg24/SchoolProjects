/**
 * Global variable for the ID of the chosen Quiz.
 */
let quizID = new URL(location.href).searchParams.get("quizID");
let questionNumber = 0;
let countCorrectAnswers = 0;

/**
 * Gets the description of a given id in the Data Sheet.
 * @param {DataArray} quizID 
 */
function getDescription(quizID) {
    console.log(quizID);
    //Searches the array for the elements with ID equal to our quizID.
    var found = data.find(function(element) {
        return element.id === quizID;
    });
    //Returns the description of found element.
    return found.description;
}

function integrerTitre2() {
    let ceQuiz = data.find(element => element.id === quizID);
    return ceQuiz.description;
}

/**
 * Changes the welcome message depending on the chosen quiz.
 */
function formatWelcomeMsg() {
    let welcomeMsg = $("#welcomeMsg");
    let description = getDescription(quizID);
    welcomeMsg.html("Vous avez choisi le thème : " +
        "<span id='description'>" + description + "</span>");
}

/**
 * Gets the elemnt of a given id in the Data Sheet.
 * @param {DataArray} quizID 
 */
function getDataElementById(quizID) {
    //Searches the array for the elements with ID equal to our quizID.
    var found = data.find(function(element) {
        return element.id === quizID;
    });
    //Returns the found element.
    return found;
}

/**
 * Asks the first question.
 */
function askQuestion() {
    let thisQuiz = getDataElementById(quizID);
    let numberOfQuestions = thisQuiz.questions.length;
    $("#questionNb").text("Question " + (questionNumber + 1) +
        "/" + numberOfQuestions + " :");

    let thisQuestion = thisQuiz.questions[questionNumber].question;
    let questionArea = $("#questionArea");
    questionArea.text(thisQuestion);
}

/**
 * Shows the answers for the first question. 
 * Each split word can be clicked and transferred to the answer area.
 */
function showAnswers() {
    let thisQuiz = getDataElementById(quizID);
    let suggestionsArea = $("#suggestionsArea");
    let thisAnswer = thisQuiz.questions[questionNumber].answer;
    let thisExtras = thisQuiz.questions[questionNumber].extras;
    let splitWords = (thisAnswer + " " + thisExtras).split(" ");
    shuffle(splitWords);
    splitWords.forEach(element => {
        let button = $("<button class='answerElement'>");
        button.text(element);
        button.click(moveToAnswer);
        suggestionsArea.append(button);
    });
}

/**
 * Moves the clicked button to the answer area and removes it from 
 * the suggestions box.
 */
function moveToAnswer() {
    let button = $(this);
    button.click(moveBack);
    $("#suggestionsArea").remove(button);
    let answerArea = $("#answerArea");
    answerArea.append(this);
}

/**
 * Moves the clicked button back to the suggestions box and removes 
 * it from the answer area.
 */
function moveBack() {
    let button = $(this);
    button.click(moveToAnswer);
    $("#answerArea").remove(button);
    let suggestionsArea = $("#suggestionsArea");
    suggestionsArea.append(this);
}

/**
 * Shuffles the Array, provided by the professors.
 * @param {*[]} array 
 */
function shuffle(array) {
    let counter = array.length;
    while (counter > 0) {
        let index = Math.floor(Math.random() * counter);
        counter--;
        //Swap positions counter and index in the array.
        [array[counter], array[index]] = [array[index], array[counter]];
    }
}

/**
 * Returns the chosen answers as a single string.
 */
function getAnswerAsSentence() {
    let str = "";
    let answerButtons = $("#answerArea button");
    for (let i = 0; i < answerButtons.length; i++) {
        str = str + answerButtons.eq(i).text() + " ";
    }
    str = str.substring(0, str.length - 1);
    return str;
}

/**
 * Checks the Answer given.
 * Returns true if correct answer, false otherwise.
 */
function checkAnswer() {
    let stringToCheck = getAnswerAsSentence();
    let thisQuiz = getDataElementById(quizID);
    let thisAnswer = thisQuiz.questions[questionNumber].answer;
    let ok = stringToCheck === thisAnswer;
    let suggestionButtons = $("#suggestionsArea button");
    let answerButtons = $("#answerArea button");
    suggestionButtons.attr('disabled', 'disabled');
    answerButtons.attr('disabled', 'disabled');
    $(this).toggleClass("hidden");
    $("#nextQuestion").toggleClass("hidden");
    if (ok) {
        countCorrectAnswers++;
        updateCount();
        $("#correctAnswer ").toggleClass("hidden");
        $("#correctAnswer ").html("<span id='corrrect'>Joli ! +1</span>")
    } else {
        $("#correctAnswer").toggleClass("hidden");
        $("#correctAnswer").html("<span id='wrong'> Faux !</span> la bonne réponse est : " +
            "<span id='correct'>" + thisAnswer + "</span>")
    }
    return ok;
}

/**
 * Triggers the next Question when clicked.
 */
function nextQuestion() {
    $("#correctAnswer ").toggleClass("hidden");
    let thisQuiz = getDataElementById(quizID);
    let numberOfQuestions = thisQuiz.questions.length;
    $("#nextQuestion").toggleClass("hidden");
    $("#checkButton").toggleClass("hidden");
    if (questionNumber < numberOfQuestions - 1) {
        questionNumber++;
        askQuestion();
        $("#answerArea").empty();
        $("#suggestionsArea").empty();
        showAnswers();
    } else {
        endGame();
    }
}

/**
 * Reloads the same quiz.
 */
function restartQuiz() {
    let str = "jeu.html?quizID=" + quizID;
    location.href = str;
}

/**
 * Function to initialise the CHECK button.
 */
function makeCheckButtonWork() {
    let checkButton = $("#checkButton");
    checkButton.click(checkAnswer);
}

/**
 * Function to initialise the Next Question button.
 */
function makeNextButtonWork() {
    let nextButton = $("#nextQuestion");
    nextButton.click(nextQuestion);
}

/**
 * Function to initialise the Reload button.
 */
function makeReloadButtonWork() {
    let reloadButton = $("#reload");
    reloadButton.click(restartQuiz);
}

/**
 * EndGame : Clears the content page and then displays the score and a thank you message,
 * as well as reload and restart buttons.
 */
function endGame() {
    let thisQuiz = getDataElementById(quizID);
    let numberOfQuestions = thisQuiz.questions.length;
    let description = getDescription(quizID);
    let content = $("#content");
    content.toggleClass("endGame");
    content.empty();
    let finishedText = $("<p id='endText'>");
    finishedText.html("Le quiz en " + "<span id='description'>" + description +
        "</span>" + " est fini !")
    let scoreText = $("<p id='endText'>");
    scoreText.text("Score Final : " + countCorrectAnswers +
        " sur " + numberOfQuestions + " = " +
        (countCorrectAnswers / numberOfQuestions * 100).toFixed(2) + "%");
    let thankYouText = $("<p id='endText'>");
    thankYouText.text("Merci d'avoir joué à mon jeu !");
    //Defining the two buttons and their behaviour.
    //Reload Button
    let reloadButton = $("<button id='reload'>");
    reloadButton.click(restartQuiz);
    reloadButton.text("Recommencer le même quiz ?");
    //Restart Button
    let restartButton = $("<button id='restart'>");
    restartButton.text("Choisir un autre quiz.");
    restartButton.click(function() {
        location.href = 'index.html';
    });
    //The order matters !! 
    content.append(finishedText);
    content.append(scoreText);
    content.append(thankYouText);
    content.append(reloadButton);
    content.append(restartButton);
}

/**
 * Updates the count of the correct answers displayed.
 */
function updateCount() {
    let thisQuiz = getDataElementById(quizID);
    let numberOfQuestions = thisQuiz.questions.length;
    let str = countCorrectAnswers + "/" + numberOfQuestions + " réponses correctes.";
    $("#answersCounter").text(str);
}

/**
 * Wrapper to ensure the code is properly populating the select
 * and that everything occurs after loading the page elements.
 */
$(document).ready(function() {
    formatWelcomeMsg();
    makeCheckButtonWork();
    makeNextButtonWork();
    makeReloadButtonWork();
    askQuestion();
    showAnswers();
    updateCount();
    console.log(integrerTitre2());
});