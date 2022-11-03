package uz.gita.game2048.data.repository.impl

import uz.gita.game2048.data.local.MySharePref
import uz.gita.game2048.data.model.State
import uz.gita.game2048.data.repository.AppRepository
import kotlin.random.Random

class AppRepositoryImpl : AppRepository {

    companion object {
        private lateinit var obj: AppRepository
        private lateinit var sharedPref: MySharePref

        fun init() {
            if (::obj.isInitialized) return
            obj = AppRepositoryImpl()
            sharedPref = MySharePref.get()
        }

        fun getAppRepository(): AppRepository = obj
    }

    private lateinit var matrix: Array<IntArray>
    private var intervalOf4 = 0
    private var addElement = 2

    override fun isEmptyCell(): Boolean {
        for (i in matrix.indices) {
            for (j in matrix.indices) {
                if (matrix[i][j] == 0)
                    return true
            }
        }
        return false
    }

    override fun addNewElementToMatrix() {
        val coordinates = ArrayList<Pair<Int, Int>>()
        for (i in matrix.indices) {
            for (j in matrix.indices) {
                if (matrix[i][j] == 0) {
                    coordinates.add(Pair(i, j))
                }
            }
        }
        val randomIndex = Random.nextInt(0, coordinates.size)
        if(intervalOf4 == 20) {
            addElement = 4
            intervalOf4 = Random.nextInt(0, 18)
        }
        matrix[coordinates[randomIndex].first][coordinates[randomIndex].second] = addElement
        addElement = 2
        intervalOf4 ++
    }

    override fun getMatrixData(): Array<IntArray> = matrix

    override fun moveLeft(): Int {
        var score = 0
        var isChanged = false
        var zero: Boolean

        for (i in matrix.indices) {
            val temp = ArrayList<Int>()
            var bool = true
            zero = false
            for (j in matrix.indices) {
                if (matrix[i][j] == 0) {
                    zero = true
                    continue
                }
                if (zero)
                    isChanged = true
                if (temp.isEmpty()) temp.add(matrix[i][j])
                else {
                    if (temp.last() == matrix[i][j] && bool) {
                        score += temp.last() * 2
                        temp[temp.size - 1] = temp.last() * 2
                        isChanged = true
                        bool = false
                    } else {
                        bool = true
                        temp.add(matrix[i][j])
                    }
                }
                matrix[i][j] = 0
            }
            for (j in 0 until temp.size) {
                matrix[i][j] = temp[j]
            }
        }

        if (isChanged)
            return score
        return -1
    }

    override fun moveRight(): Int {
        var score = 0
        var zero: Boolean
        var isChanged = false

        for (i in matrix.indices) {
            val temp = ArrayList<Int>()
            var bool = true
            zero = false
            for (j in matrix.indices) {
                if (matrix[i][3 - j] == 0) {
                    zero = true
                    continue
                }
                if (zero)
                    isChanged = true
                if (temp.isEmpty()) temp.add(matrix[i][3 - j])
                else {
                    if (temp.last() == matrix[i][3 - j] && bool) {
                        score += temp.last() * 2
                        temp[temp.size - 1] = temp.last() * 2
                        isChanged = true
                        bool = false
                    } else {
                        bool = true
                        temp.add(matrix[i][3 - j])
                    }
                }
                matrix[i][3 - j] = 0
            }
            for (j in temp.indices) {
                matrix[i][3 - j] = temp[j]
            }
        }
        if (isChanged)
            return score
        return -1
    }

    override fun moveUp(): Int {
        var score = 0
        var isChanged = false
        var zero: Boolean

        for (j in matrix.indices) {
            val temp = ArrayList<Int>()
            var bool = true
            zero = false
            for (i in matrix.indices) {
                if (matrix[i][j] == 0) {
                    zero = true
                    continue
                }
                if (zero)
                    isChanged = true
                if (temp.isEmpty()) temp.add(matrix[i][j])
                else {
                    if (temp.last() == matrix[i][j] && bool) {
                        score += temp.last() * 2
                        temp[temp.size - 1] = temp.last() * 2
                        isChanged = true
                        bool = false
                    } else {
                        bool = true
                        temp.add(matrix[i][j])
                    }
                }
                matrix[i][j] = 0
            }
            for (i in temp.indices) {
                matrix[i][j] = temp[i]
            }
        }
        if (isChanged)
            return score
        return -1
    }

    override fun moveDown(): Int {
        var score = 0
        var isChanged = false
        var zero: Boolean

        for (j in matrix.indices) {
            val temp = ArrayList<Int>()
            var bool = true
            zero = false
            for (i in matrix.indices) {
                if (matrix[3 - i][j] == 0) {
                    zero = true
                    continue
                }
                if (zero)
                    isChanged = true
                if (temp.isEmpty()) temp.add(matrix[3 - i][j])
                else {
                    if (temp.last() == matrix[3 - i][j] && bool) {
                        score += temp.last() * 2
                        temp[temp.size - 1] = temp.last() * 2
                        bool = false
                        isChanged = true
                    } else {
                        bool = true
                        temp.add(matrix[3 - i][j])
                    }
                }
                matrix[3 - i][j] = 0
            }
            for (i in temp.indices) {
                matrix[3 - i][j] = temp[i]
            }
        }
        if (isChanged)
            return score
        return -1
    }

    override fun saveRecord(rec: Int) {
        sharedPref.saveRecord(rec)
    }

    override fun saveState(state: State) {
        sharedPref.saveState(state)
    }

    override fun getState(): State = with(sharedPref) {
        val state = getState()
        matrix = state.array
        if (!sharedPref.isFirstTime())
            return state
        addNewElementToMatrix()
        addNewElementToMatrix()
        return state
    }

    override fun getNewArray(): Array<IntArray> {
        for(i in 0..3) {
            for(j in 0..3) {
                matrix[i][j] = 0
            }
        }
        addNewElementToMatrix()
        addNewElementToMatrix()
        return matrix
    }
}