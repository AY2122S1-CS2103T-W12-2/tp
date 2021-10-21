package seedu.address.model.task;

import seedu.address.model.group.Group;

public class Task {
    protected Description description;
    protected Group group;
    protected TaskType type;
    protected Date date;
    protected boolean isDone;
    protected RecurringFrequency recurringFrequency;
    protected Priority priority;

    /**
     * Constructor for Task.
     * @param description Description of task
     * @param group Group of task
     * @param type Type of task (Deadline, Event, Todo)
     * @param date Date of task
     */
    public Task(Description description, Group group, Date date, TaskType type,
                RecurringFrequency recurringFrequency, Priority priority) {
        this.description = description;
        this.group = group;
        this.type = type;
        this.date = date;
        this.isDone = false;
        this.recurringFrequency = recurringFrequency;
        this.priority = priority;
    }

    /**
     * Returns X if isDone true.
     *
     * @return "X" if isDone true.
     */
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }
    public Description getDescription() {
        return description;
    }
    public Group getGroup() {
        return group;
    }
    public Date getDate() {
        return date;
    }
    public TaskType getTaskType() {
        return type;
    }
    public RecurringFrequency getRecurringFrequency() {
        return recurringFrequency;
    }
    public Priority getPriority() {
        return priority;
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return String.format("%1$s %2$s", getStatusIcon(), description.toString());
    }

    /**
     * Comparator for the task's description.
     * @param otherTask the otherTask
     * @return an integer for comparison
     */
    public int compareDescription(Task otherTask) {
        return this.getDescription().compareTo(otherTask.getDescription());
    }

    /**
     * Updates a recurring task's date to the current week/month/year.
     */
    public void updateRecurringTaskDate() {
        switch (recurringFrequency.toString()) {
        case "week":
            if (date.isLastWeek()) {
                this.date = date.getDateForThisWeek();
            }
            break;
        case "month":
            if (date.isLastMonth()) {
                this.date = date.getDateForThisMonth();
            }
            break;
        case "year":
            if (date.isLastYear()) {
                this.date = date.getDateForThisYear();
            }
            break;
        default:
            break;
        }
    }

    /**
     * Comparator for the task's date.
     * @param otherTask the otherTask
     * @return an integer for comparison
     */
    public int compareDate(Task otherTask) {
        return this.getDate().compareTo(otherTask.getDate());
    }

    /**
     * Comparator for the task's group.
     * @param otherTask the otherTask
     * @return an integer for comparison
     */
    public int compareGroup(Task otherTask) {
        return this.getGroup().compareTo(otherTask.getGroup());
    }

    /**
     * Equals method.
     *
     * @param obj the object
     * @return true if they are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task otherTask = (Task) obj;
            return getDescription().equals(otherTask.getDescription())
                    && getGroup().equals(otherTask.getGroup())
                    && getTaskType().equals(otherTask.getTaskType())
                    && getDate().equals(otherTask.getDate())
                    && isDone == otherTask.isDone
                    && getRecurringFrequency().equals(otherTask.getRecurringFrequency());
        } else {
            return false;
        }
    }
}
