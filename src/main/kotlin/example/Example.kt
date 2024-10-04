package example

enum class Status {
    DONE,
    CANCELLED,
    IN_PROGRESS,
    DRAFT,
}

fun calculateStatus(status: String) =
    when (status) {
        "in_review" -> Status.DRAFT
        "draft" -> Status.DRAFT
        "cancelled" -> Status.CANCELLED
        "done" -> Status.DONE
        else -> Status.IN_PROGRESS
    }
