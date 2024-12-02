package com.example.app

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.foodmood.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Инфлейт макета с использованием View Binding
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Настраиваем обработчики кнопок через binding
        binding.settingsChangePassButton.setOnClickListener {
            showDialog("Изменить пароль", "Здесь можно изменить пароль.")
        }

        binding.settingsSupportButton.setOnClickListener {
            showDialog("Поддержка", "Напишите в службу поддержки для получения помощи.")
        }

        binding.settingsUserAgreeButton.setOnClickListener {
            showDialog("Пользовательское соглашение", "Текст пользовательского соглашения.")
        }

        binding.settingsNotificationsButton.setOnClickListener {
            showDialog("Настройки уведомлений", "Настройте параметры уведомлений.")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Освобождаем binding
    }

    // Метод для отображения диалогов
    private fun showDialog(title: String, message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("ОК") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}
