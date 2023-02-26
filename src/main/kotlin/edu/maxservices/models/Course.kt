package edu.maxservices.models

class Course (
    private val id: Int,
    private val name: String,
    private val description: String,
    private val prevMinScore: Int,
    private val budgetPlaces: Int,
    private val commercePlaces: Int,
    private val exams: List<Exams>,
    private val applicants: MutableList<Student>,
) {
    // Add applicant to list and
    // add this course to applicant's list
    fun newApplicant(newbie: Student) {
        applicants.add(newbie)
        newbie.getLinkApplies().add(this.id)
    }
    // Sort applicants according to exams required in this course
    fun sortApplicants() : List<Pair<Student, Int>> {
        val apls : MutableList<Pair<Student, Int>> = mutableListOf()
        for (i in 0 until applicants.size) {
            apls.add(i, Pair(applicants[i], applicants[i].getScore(exams)))
        }
        return apls.sortedWith(compareBy { it.second }).asReversed().toList()
    }
    // Get applicants guaranteed to be on a budget
    fun budgetApplicants() : List<Pair<Student, Int>> {
        return sortApplicants().slice(0 until budgetPlaces)
    }
    // Get applicants guaranteed to be on commerce
    fun commerceApplicants() : List<Pair<Student, Int>> {
        return sortApplicants().slice(budgetPlaces until budgetPlaces + commercePlaces)
    }
    // Getters
    fun getId() : Int {
        return id
    }
    fun getName() : String {
        return name
    }
    fun getDescription() : String {
        return description
    }
    fun getPrevMinScore() : Int {
        return prevMinScore
    }
    fun getBudgetPlaces() : Int {
        return budgetPlaces
    }
    fun getCommercePlaces() : Int {
        return commercePlaces
    }
    fun getExams(): List<Exams> {
        return exams.toList()
    }
    fun getApplicants(): List<Student> {
        return applicants.toList()
    }
}