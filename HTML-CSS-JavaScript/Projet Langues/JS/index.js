/**
 * Wrapper to ensure the code is properly populating the select.
 */
$(document).ready(function() {
    populateChoices(data);
});

/**
 * Populates the initial dropdown list with choices from the datasheet.
 * @param {Data List} data 
 */
function populateChoices(data) {
    let choice = $("#languageChoice");
    for (let i = 0; i < data.length; i++) {
        let option = new Option(data[i].description, data[i].id);
        choice.append(option);
    }
}