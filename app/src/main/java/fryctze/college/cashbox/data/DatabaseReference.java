package fryctze.college.cashbox.data;

public class DatabaseReference {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "cashbox";

    public static final String TABLE_TRANSACTION = "transactions",
            TRANSACTION_COL_ID = "id",
            TRANSACTION_COL_NAME = "name",
            TRANSACTION_COL_DATE = "date",
            TRANSACTION_COL_NOMINAL = "nominal",
            TRANSACTION_COL_IS_GAIN = "is_gain",
            TRANSACTION_COL_DESC = "description";

    public static final String TABLE_GOAL = "goal",
            GOAL_COL_ID = "id",
            GOAL_COL_NAME = "name",
            GOAL_COL_DATE = "date",
            GOAL_COL_NOMINAL = "nominal",
            GOAL_COL_DESC = "description";
}
