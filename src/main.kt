import java.text.DecimalFormat
// notes:
//    val tax = .07
//    val cost = 100000
//
//    val twoDigits = DecimalFormat("#,###.00%")
//    var totalCost = (cost * tax).toFloat()
//
//    var ourTestVar = twoDigits.format(totalCost)
//    println("result: $ourTestVar")

// menu
//// add purchase
//// remove purchase
//// recommend tip
fun menu(): Int{
    var selectionNum: Int
    print("\n ***** Please select an option *****\n" +
            "1. Add purchase\n" +
            "2. Get total and calculate tip\n" +
            "3. Exit\n")
    selectionNum = readLine()!!.toInt()
    return selectionNum
}
fun showItems(currentPurchasedItems:MutableList<String>) {
    println("current billable items:\n")
    for (i in currentPurchasedItems.indices) {
        println("item ${i+1}: ${currentPurchasedItems[i]} ")
    }

}
// add new item function
// ask for an initial value to be entered for itemsPurchased from Main
// give user opportunity to enter additional values
// return values to itemsPurchased container from Main
fun addNewItem(currentPurchasedItems:MutableList<String>): MutableList<String> {
    if (currentPurchasedItems.count() == 0) {
        print("***** please enter your first amount *****\n")
        var newValue: String? = readLine()
        if (newValue != null) {
            currentPurchasedItems.add(newValue)
        } else {
            println("that is not a valid number. returning to menu")
            return currentPurchasedItems
        }
    }
    println(
        "current items added to list: \n" +
                "$currentPurchasedItems"
    )

    var working = true
    while (working) {
        print("would you like to add more items y/n ? \n")
        var answer: String? = readLine()
        if (answer?.lowercase() == "n") {
            showItems(currentPurchasedItems)
            println("\n***** Back to main menu *****")
            return currentPurchasedItems
        }
        else if (answer?.lowercase() == "y"){
            showItems(currentPurchasedItems)
            print("please enter a dollar amount\n")
            var newValue: String? = readLine()
            if (newValue != null) {
                currentPurchasedItems.add(newValue)
            } else {
                println("that is not a valid number. returning to menu")
                return currentPurchasedItems
            }
        }

    }
    return currentPurchasedItems
}
// used in askTipCalc from main menu
// return tip value String based on
// bill and tip percent
fun getTipAmount(totalBill: Double, tipPercent:Double): String{
    var totalTip = totalBill * tipPercent
    return convertToDollars(totalTip)
}

// used in askTipCalc and getTipAmount
// to convert Double value to dollars and cents
// String datatype
fun convertToDollars(total: Double): String{
    val twoDigits = DecimalFormat("$#,###.00")
    return twoDigits.format((total))
}

// used in askTipCalc to add total
// of user entered values from main menu
fun getTotalBill(currentPurchasedItems:MutableList<String>): Double {
    var total = 0.0
    for (i in  currentPurchasedItems.indices){
        total += currentPurchasedItems[i].toDouble()
    }
    return total
}

// give customer total, ask what tip %, return tip % in dollar, cents format
fun askTipCalc(currentPurchasedItems:MutableList<String>): MutableList<String> {
    if (currentPurchasedItems.size <= 0) {
        var noItems = addNewItem(currentPurchasedItems)
        return noItems
    }
        showItems(currentPurchasedItems)
        println("total bill: ${convertToDollars(getTotalBill(currentPurchasedItems))}\n")
        print("***** please indicate the amount you wish to tip *****\n")
        var selectionNum: Int
        print(  "1. 10%: \n" +
                "2. 15% \n" +
                "3. 17.5% \n" +
                "4. 20% \n\n")
        selectionNum = readLine()!!.toInt()
        when (selectionNum) {
            1 -> println("total bill: ${getTotalBill(currentPurchasedItems)}\n" +
                         "total for 10% tip: ${getTipAmount(getTotalBill(currentPurchasedItems), .10)}\n")
            2 -> println("total bill: ${getTotalBill(currentPurchasedItems)}\n" +
                         "total for 15% tip:${getTipAmount(getTotalBill(currentPurchasedItems), .15)}\n")
            3 -> println("total bill: ${getTotalBill(currentPurchasedItems)}\n" +
                         "total for 17.5% tip:${getTipAmount(getTotalBill(currentPurchasedItems), .175)}\n")
            4 -> println("total bill: ${getTotalBill(currentPurchasedItems)}\n" +
                         "total for 20% tip:${getTipAmount(getTotalBill(currentPurchasedItems), .20)}\n")
            !in 1..3 -> println("that is an invalid selection")
            else -> return currentPurchasedItems

        }
    return currentPurchasedItems
}

// show all items in a Mutable list
// used in askTipCalc and addNewItems
// to show user currently entered values


fun main() {

    // container for user entered values of cost of item
    var itemsPurchased = mutableListOf<String>()
    // bool value used to nav menu and exit app
    var finished = false
    // main loop for menu
    while (finished != true) {
        when (menu()) {
            1 -> itemsPurchased = addNewItem(itemsPurchased)
            2 -> itemsPurchased = askTipCalc(itemsPurchased)
            3 -> break
            !in 1..3 -> println("that is an invalid selection")
            else -> println("That is not a valid selection")
        }
    }
}





