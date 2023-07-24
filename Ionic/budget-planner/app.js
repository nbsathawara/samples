const txtReason = document.querySelector('#txtReason')
const txtAmount = document.querySelector('#txtAmount')
const btnAdd = document.querySelector('#btnAdd')
const btnCancel = document.querySelector('#btnCancel')
const expenseList = document.querySelector('#expenses')
const txtTotalExpense = document.querySelector('#txtTotalExpense')
const alertController = document.querySelector('ion-alert-controller')


let totalExpense = 0

btnAdd.addEventListener('click', () => {
    const reason = txtReason.value
    const amount = txtAmount.value

    if (reason.trim().length <= 0 || amount.trim().length <= 0 || amount <= 0) {
        showAlert('Error', 'Invalid Reason or Amount!!!', 'OK')
        return
    }
    addExpenseItem(reason, amount)
    clear()
})

btnCancel.addEventListener('click', () => {
    clear()
})

const addExpenseItem = (reason, amount) => {
    const item = document.createElement('ion-item')
    item.textContent = reason + " : $ " + amount

    expenseList.appendChild(item)

    totalExpense += +amount
    txtTotalExpense.textContent = totalExpense

    clear()
}


const clear = () => {
    txtReason.value = ''
    txtAmount.value = ''
}

 const showAlert = async (title, msg, btnText) =>  {
  const alert= document.createElement('ion-alert')
  alert.header=title
  alert.message=msg
  alert.buttons=[btnText]

  document.body.appendChild(alert)
  await alert.present()
}
