/**
 * Enterprise Task & Operations Tracker
 * Author: Bryan Wesley Santana
 * Module 3: Language - Kotlin (CSE 310)
 */

// Data class representing an operational task item (Meets Data Class requirement)
data class TaskItem(
    val id: Int,
    val title: String,
    val priority: String,
    var isCompleted: Boolean,
    val timestamp: String
)

/// Core manager class handling task collections and filtering
class TaskManager {
    // Mutable collection holding task items (Meets Collection requirement)
    private val tasks = mutableListOf<TaskItem>()
    private var nextId = 1

    /// Adds a new operational task to the system
    fun addTask(title: String, priority: String) {
        val newTask = TaskItem(
            id = nextId++,
            title = title,
            priority = priority.uppercase(),
            isCompleted = false,
            timestamp = "2026-08-08 15:00"
        )
        tasks.add(newTask)
        println("[SUCCESS] Task added successfully: '${newTask.title}' [Priority: ${newTask.priority}]")
    }

    /// Marks a task as completed using safe indexing and conditionals
    fun completeTask(id: Int) {
        val task = tasks.find { it.id == id }
        if (task != null) {
            task.isCompleted = true
            println("[UPDATE] Task #${task.id} marked as Completed.")
        } else {
            println("[ERROR] Task with ID $id not found.")
        }
    }

    /// Displays all tasks currently registered in the system
    fun displayAllTasks() {
        println("\n==================================================")
        println("               CURRENT TASK REGISTRY              ")
        println("==================================================")
        if (tasks.isEmpty()) {
            println("No operational tasks registered.")
        } else {
            for (task in tasks) {
                val status = if (task.isCompleted) "[X]" else "[ ]"
                println("ID: ${task.id} | $status ${task.title} | Priority: ${task.priority}")
            }
        }
        println("==================================================\n")
    }

    /// Filters tasks using higher-order lambda functions and the 'when' expression
    fun filterTasksByPriority(priorityLevel: String) {
        println("\n--- FILTERED REPORT: PRIORITY $priorityLevel ---")
        val filtered = tasks.filter { it.priority == priorityLevel.uppercase() }

        // Demonstrating varied uses of the 'when' expression
        val message = when (priorityLevel.uppercase()) {
            "HIGH" -> "Critical operational items requiring immediate attention."
            "MEDIUM" -> "Standard priority workflow items."
            "LOW" -> "Routine maintenance or backlog tasks."
            else -> "Unknown priority level specified."
        }
        println("Note: $message")

        if (filtered.isEmpty()) {
            println("No tasks found matching this priority.")
        } else {
            filtered.forEach { println("-> [${it.id}] ${it.title} (Completed: ${it.isCompleted})") }
        }
        println("-----------------------------------------------\n")
    }

    /// Computes summary metrics using collections
    fun printAnalytics() {
        val total = tasks.size
        val completedCount = tasks.count { it.isCompleted }
        val pendingCount = total - completedCount

        println("\n==================================================")
        println("               OPERATIONAL ANALYTICS              ")
        println("==================================================")
        println("Total Tasks Recorded : $total")
        println("Completed Tasks      : $completedCount")
        println("Pending Tasks        : $pendingCount")
        println("==================================================\n")
    }
}

/// Main entry point demonstrating execution flow, loops, and variables
fun main() {
    println("==================================================")
    println("   BYU CSE 310 - Enterprise Task & Operations     ")
    println("==================================================")

    // Immutable (val) and Mutable (var) variables demonstration
    val appName = "TaskTracker-Kotlin"
    var activeSession = true
    println("Initializing $appName engine...\n")

    val manager = TaskManager()

    // Pre-populating sample data
    manager.addTask("Configure AWS Cloud Deployment", "HIGH")
    manager.addTask("Update Frontall ERP Documentation", "MEDIUM")
    manager.addTask("Review Kotlin Coroutine Patterns", "LOW")

    // Modifying tasks using loops and conditions
    manager.completeTask(1)

    // Displaying current tasks
    manager.displayAllTasks()

    // Filtering utilizing lambda expressions and 'when'
    manager.filterTasksByPriority("HIGH")
    manager.filterTasksByPriority("MEDIUM")

    // Generating analytics summary
    manager.printAnalytics()

    println("Application execution completed successfully.")
}