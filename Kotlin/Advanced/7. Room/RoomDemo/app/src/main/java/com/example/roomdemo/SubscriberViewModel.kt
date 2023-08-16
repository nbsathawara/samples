package com.example.roomdemo

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.roomdemo.db.Subscriber
import com.example.roomdemo.db.SubscriberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SubscriberViewModel(private val repository: SubscriberRepository) : ViewModel() {

    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()

    val btnSaveText = MutableLiveData<String>()
    val btnClearText = MutableLiveData<String>()

    val subscribers = repository.subscribers

    var selectedSubscriber: Subscriber? = null

    private val statusMessage = MutableLiveData<Event<String>>()
    val statusMsg: LiveData<Event<String>>
        get() = statusMessage

    init {
        btnSaveText.value = "Save"
        btnClearText.value = "Clear All"
    }

    fun saveOrUpdate(subscriber: Subscriber?) {
        if (name.value.isNullOrEmpty())
            statusMessage.value = Event("Please enter valid name!!!")
        else if (email.value.isNullOrEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email.value!!)
                .matches()
        )
            statusMessage.value = Event("Please enter valid email!!!")
        else {
            if (subscriber == null) {
                insert(Subscriber(0, name.value!!, email.value!!))
                name.value = ""
                email.value = ""
            } else {
                subscriber.name = name.value!!
                subscriber.email = email.value!!
                update(subscriber)
            }
        }
    }

    fun clearAllOrDelete() {
        if (selectedSubscriber != null)
            delete(selectedSubscriber)
        else
            deleteAll()
    }

    fun subscriberSelected(subscriber: Subscriber) {
        name.value = subscriber.name
        email.value = subscriber.email
        selectedSubscriber = subscriber

        btnSaveText.value = "Update"
        btnClearText.value = "Delete"
    }

    private fun insert(subscriber: Subscriber) =
        viewModelScope.launch(Dispatchers.IO) {
            val rowId = repository.insert(subscriber)
            withContext(Dispatchers.Main) {
                if (rowId > -1)
                    statusMessage.value = Event("Subscriber inserted successfully with id : $rowId")
                else
                    statusMessage.value = Event("Error occurred while insertion!!!!")
            }
        }


    private fun update(subscriber: Subscriber) =
        viewModelScope.launch(Dispatchers.IO) {
            val count = repository.update(subscriber)
            withContext(Dispatchers.Main) {
                if (count > 0)
                    statusMessage.value = Event("$count rows updated successfully...")
                else
                    statusMessage.value = Event("Error occurred while update!!!!")
            }
        }

    private fun delete(subscriber: Subscriber?) =
        viewModelScope.launch(Dispatchers.IO) {
            val count = repository.delete(subscriber!!)
            withContext(Dispatchers.Main) {
                if (count > 0) {
                    name.value = ""
                    email.value = ""
                    selectedSubscriber = null

                    btnSaveText.value = "Save"
                    btnClearText.value = "Clear All"
                    statusMessage.value = Event("$count rows deleted successfully...")
                } else
                    statusMessage.value = Event("Error occurred while delete!!!!")
            }
        }

    private fun deleteAll() =
        viewModelScope.launch(Dispatchers.IO) {
            val count = repository.deleteAll()
            withContext(Dispatchers.Main) {
                if (count > 0)
                    statusMessage.value = Event("$count rows Subscribers deleted successfully...")
                else
                    statusMessage.value = Event("Error occurred while delete!!!!")
            }
        }

    class SubscriberViewModelFactory(private val repository: SubscriberRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SubscriberViewModel::class.java)) {
                return SubscriberViewModel(repository) as T
            }
            throw java.lang.IllegalArgumentException("Unknown ViewModel Class...")
        }
    }
}