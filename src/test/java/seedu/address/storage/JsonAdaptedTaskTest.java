package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.DEADLINE1;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.group.Group;
import seedu.address.model.task.Date;
import seedu.address.model.task.Description;
import seedu.address.model.task.TaskType;

public class JsonAdaptedTaskTest {
    private static final String INVALID_DESCRIPTION = " ";
    private static final String INVALID_TASKTYPE = "anytime";
    private static final String INVALID_DATE = "7771-21-33";
    private static final String INVALID_GROUP = "cs1234";

    private static final String VALID_DESCRIPTION = DEADLINE1.getDescription().toString();
    private static final String VALID_STATUS = DEADLINE1.getStatusIcon();
    private static final String VALID_TASKTYPE = DEADLINE1.getTaskType().toString();
    private static final String VALID_DATE = DEADLINE1.getDate().toString();
    private static final String VALID_GROUP = DEADLINE1.getGroup().toString();

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(DEADLINE1);
        assertEquals(DEADLINE1, task.toModelType());
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(INVALID_DESCRIPTION, VALID_STATUS,
            VALID_GROUP, VALID_DATE, VALID_TASKTYPE, "med", "none");
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, VALID_STATUS,
            VALID_GROUP, VALID_DATE, VALID_TASKTYPE, null, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidGroup_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, VALID_STATUS,
            INVALID_GROUP, VALID_DATE, VALID_TASKTYPE, "med", "none");
        String expectedMessage = Group.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullGroup_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, VALID_STATUS,
            null, VALID_DATE, VALID_TASKTYPE, null, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Group.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, VALID_STATUS,
            VALID_GROUP, INVALID_DATE, VALID_TASKTYPE, "med", "none");
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTasktype_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, VALID_STATUS,
            VALID_GROUP, VALID_DATE, INVALID_TASKTYPE, "med", "none");
        String expectedMessage = TaskType.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTasktype_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, VALID_STATUS,
            VALID_GROUP, VALID_DATE, null, "med", "none");
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskType.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }
}
