package cr.una.ac.control_arterial_sebas_edgar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import cr.una.ac.control_arterial_sebas_edgar.databinding.FragmentSecondBinding
import cr.una.ac.control_arterial_sebas_edgar.entity.TomarArterial
import cr.una.ac.control_arterial_sebas_edgar.viewModel.TomaArterialViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var tomaArterialViewModel : TomaArterialViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        tomaArterialViewModel = ViewModelProvider(requireActivity()).get(TomaArterialViewModel::class.java)


        val editTextDiastolica = view.findViewById<EditText>(R.id.editText_diastolica)
        val editTextSistolica = view.findViewById<EditText>(R.id.editText_sistolica)
        val editTextRitmo = view.findViewById<EditText>(R.id.editText_ritmo)

        binding.buttonSubmit.setOnClickListener{
            val uuid = null
            val diastolica = editTextDiastolica.text.toString().toIntOrNull() ?: 0
            val sistolica = editTextSistolica.text.toString().toIntOrNull() ?: 0
            val ritmo = editTextRitmo.text.toString().toIntOrNull() ?: 0

            val newArterial = TomarArterial(uuid, diastolica, sistolica, ritmo)
            GlobalScope.launch(Dispatchers.IO) {
                tomaArterialViewModel.addTomaArterial(newArterial)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}