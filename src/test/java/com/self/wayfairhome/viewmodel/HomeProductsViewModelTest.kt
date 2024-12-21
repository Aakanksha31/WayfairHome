import com.self.wayfairhome.data.repo.ProductsRepository
import com.self.wayfairhome.model.OneOffEvent
import com.self.wayfairhome.viewmodel.HomeProduct
import com.self.wayfairhome.viewmodel.HomeProductsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeProductsViewModelTest {

    private lateinit var viewModel: HomeProductsViewModel

    @Mock
    private lateinit var mockProductsRepository: ProductsRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @Test
    fun `getProducts updates uiState with products on success`() = runTest {
        val list = listOf<HomeProduct>()
        `when`(mockProductsRepository.getProducts()).thenReturn(list)

        viewModel = HomeProductsViewModel(mockProductsRepository)

        advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assert(!uiState.isLoading)
        assert(uiState.items == list)
    }

    @Test
    fun `getProducts sends oneOffEvent on error`() = runTest {
        val errorMessage = "Error fetching products"

        `when`(mockProductsRepository.getProducts()).thenThrow(RuntimeException(errorMessage))

        viewModel = HomeProductsViewModel(mockProductsRepository)

        advanceUntilIdle()
        val oneOffEvent = viewModel.oneOffEvent.first()

        val uiState = viewModel.uiState.value
        assert(!uiState.isLoading)
        assert(uiState.items.isEmpty())

        assert(oneOffEvent is OneOffEvent.ShowError)
    }
}
