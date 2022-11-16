package com.theshine.android.lites.ui.view.main.home.graph

import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentGraphBinding
import com.theshine.android.lites.ui.view.main.MainViewModel
import com.theshine.android.lites.util.Event
import com.theshine.android.lites.util.EventObserver
import org.koin.android.viewmodel.ext.android.sharedViewModel


class GraphFragment: BaseVmFragment<FragmentGraphBinding>(
    R.layout.fragment_graph,
    GraphViewModel::class.java
) {
    override val viewModel by lazy { vm as GraphViewModel }
    val activityViewModel by sharedViewModel<MainViewModel>()




    override fun initFragment() {

        activityViewModel.myPet.value?.name?.let { viewModel.setPetName(it) }
        viewModel.setObserves()
        viewModel.getWeekPetData()

    }

    private fun GraphViewModel.setObserves(){

        action.observe(this@GraphFragment, EventObserver{
            when(it){
                GraphViewModel.GraphAction.WEEK ->{
                    weekDataSet()
                }
                GraphViewModel.GraphAction.MONTH ->{
                    monthDataSet()
                }
                GraphViewModel.GraphAction.YEAR ->{
                    yearDataSet()
                }
            }
        })

    }


    fun weekDataSet(){
        binding.barChart.visibility = View.GONE
        binding.lineChart.visibility = View.VISIBLE

        val values1 = ArrayList<Entry>()
        val values2 = ArrayList<Entry>()

        values2.add(Entry(1.toFloat(), 0.0.toFloat()))
        values2.add(Entry(2.toFloat(), 0.0.toFloat()))
        values2.add(Entry(3.toFloat(), 0.0.toFloat()))
        values2.add(Entry(4.toFloat(), 0.0.toFloat()))
        values2.add(Entry(5.toFloat(), 0.0.toFloat()))
        values2.add(Entry(6.toFloat(), 0.0.toFloat()))
        values2.add(Entry(7.toFloat(), 0.0.toFloat()))
        viewModel.myPetData.value?.forEachIndexed { index, weekPetData ->
            when(weekPetData.wk_han){
                "월" -> values1.add(Entry(1.toFloat(), weekPetData.weight.toFloat()))
                "화" -> values1.add(Entry(2.toFloat(), weekPetData.weight.toFloat()))
                "수" -> values1.add(Entry(3.toFloat(), weekPetData.weight.toFloat()))
                "목" -> values1.add(Entry(4.toFloat(), weekPetData.weight.toFloat()))
                "금" -> values1.add(Entry(5.toFloat(), weekPetData.weight.toFloat()))
                "토" -> values1.add(Entry(6.toFloat(), weekPetData.weight.toFloat()))
                "일" -> values1.add(Entry(7.toFloat(), weekPetData.weight.toFloat()))
            }
        }

        // 생성된 데이터를 담음
        var set1 = LineDataSet(values1, "DataSet 1")
        var set2 = LineDataSet(values2, "dataSet 2")

        set1.setCircleColor(ContextCompat.getColor(requireContext(), R.color.green_40C5))
        set1.color = ContextCompat.getColor(requireContext(), R.color.green_40C5)
        set1.valueTextColor = ContextCompat.getColor(requireContext(), R.color.green_40C5)
        set1.circleHoleColor = ContextCompat.getColor(requireContext(), R.color.green_40C5)

        set2.setCircleColor(android.R.color.transparent)
        set2.color = ContextCompat.getColor(requireContext(), android.R.color.transparent)
        set2.valueTextColor = ContextCompat.getColor(requireContext(), android.R.color.transparent)
        set2.circleHoleColor = ContextCompat.getColor(requireContext(), android.R.color.transparent)

        val lineData = LineData()
        lineData.addDataSet(set1)
        lineData.addDataSet(set2)

        val xAxis = binding.lineChart.xAxis
        //Label 표시 여부
        xAxis.setDrawLabels(true)
        //x축 Label이 나타날 위치 설정
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        //x축 최소값 설정
        xAxis.axisMinimum = 0.5f
        //Grid, AxisLine 표시 설정
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)
        xAxis.granularity = 1f
        val weekdays = arrayOf("", "월", "화", "수", "목", "금", "토", "일")
        xAxis.valueFormatter = IndexAxisValueFormatter(weekdays)

        val yAxis: YAxis
        yAxis = binding.lineChart.axisLeft
        binding.lineChart.axisRight.isEnabled = false
        xAxis.textSize = 10f
        yAxis.textSize = 10f
        yAxis.textColor = Color.BLACK
        yAxis.axisLineColor = android.R.color.transparent

        yAxis.setDrawAxisLine(false)
        yAxis.setDrawGridLines(true)
        yAxis.axisMaximum = 32f
        yAxis.axisMinimum = 0f

        // 차트 하단에 표시되는 description 표시 여부 설정
        binding.lineChart.description.isEnabled = false
        // 차트 왼쪽 하단에 표시되는 legent 표시 여부 설정
        binding.lineChart.legend.isEnabled = false
        // 차트에 데이터 설정
        binding.lineChart.data = lineData
        // 차트 높이값 설정
        //binding.lineChart.layoutParams.height = 800
        // 차트 HightLight 표시 여부 설정
        binding.lineChart.data.isHighlightEnabled = false
        binding.lineChart.animateY(1000)

        binding.lineChart.invalidate()
    }


    fun monthDataSet(){
        binding.lineChart.visibility = View.GONE
        binding.barChart.visibility = View.VISIBLE

        val values1 = ArrayList<BarEntry>()
        val values2 = ArrayList<BarEntry>()
        val day = ArrayList<String>()
        day.add("")
        for(i in 1..31){
            values2.add(BarEntry(i.toFloat(), 0.0.toFloat()))
            day.add(i.toString())
        }

        viewModel.myPetData.value?.forEachIndexed { index, weekPetData ->
            values1.add(BarEntry(weekPetData.wk_han.toFloat(), weekPetData.weight.toFloat()))
        }

        // 생성된 데이터를 담음
        var set1 = BarDataSet(values1, "DataSet 1")
        var set2 = BarDataSet(values2, "dataSet 2")

        //set1.setCircleColor(ContextCompat.getColor(requireContext(), R.color.green_40C5))
        set1.color = ContextCompat.getColor(requireContext(), R.color.green_40C5)
        set1.valueTextColor = ContextCompat.getColor(requireContext(), android.R.color.transparent)
        //set1.circleHoleColor = ContextCompat.getColor(requireContext(), R.color.green_40C5)

        //set2.setCircleColor(android.R.color.transparent)
        set2.color = ContextCompat.getColor(requireContext(), android.R.color.transparent)
        set2.valueTextColor = ContextCompat.getColor(requireContext(), android.R.color.transparent)
        //set2.circleHoleColor = ContextCompat.getColor(requireContext(), android.R.color.transparent)

        val lineData = BarData(set1)
        lineData.addDataSet(set1)
        lineData.addDataSet(set2)

        lineData.barWidth = 0.35f



        val xAxis = binding.barChart.xAxis
        //Label 표시 여부
        xAxis.setDrawLabels(true)
        //x축 Label이 나타날 위치 설정
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        //x축 최소값 설정
        xAxis.axisMinimum = 0.5f
        //Grid, AxisLine 표시 설정
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)
        xAxis.granularity = 1f

        xAxis.valueFormatter = IndexAxisValueFormatter(day)

        val yAxis: YAxis
        yAxis = binding.barChart.axisLeft
        binding.barChart.axisRight.isEnabled = false
        xAxis.textSize = 10f
        yAxis.textSize = 10f
        yAxis.textColor = Color.BLACK
        yAxis.axisLineColor = android.R.color.transparent

        yAxis.setDrawAxisLine(false)
        yAxis.setDrawGridLines(true)
        yAxis.axisMaximum = 32f
        yAxis.axisMinimum = 0f

        // 차트 하단에 표시되는 description 표시 여부 설정
        binding.barChart.description.isEnabled = false
        // 차트 왼쪽 하단에 표시되는 legent 표시 여부 설정
        binding.barChart.legend.isEnabled = false
        // 차트에 데이터 설정
        binding.barChart.data = lineData
        // 차트 높이값 설정
        //binding.barChart.layoutParams.height = 800
        // 차트 HightLight 표시 여부 설정
        binding.barChart.data.isHighlightEnabled = false
        binding.barChart.animateY(1000)

        binding.barChart.invalidate()
    }

    fun yearDataSet(){
        binding.lineChart.visibility = View.GONE
        binding.barChart.visibility = View.VISIBLE

        val values1 = ArrayList<BarEntry>()
        val values2 = ArrayList<BarEntry>()
        val day = ArrayList<String>()
        day.add("")
        for(i in 1..12){
            values2.add(BarEntry(i.toFloat(), 0.0.toFloat()))
            day.add(i.toString())
        }

        viewModel.myPetYearData.value?.forEachIndexed { index, weekPetData ->
            val x = weekPetData.wk_han.split("-")
            values1.add(BarEntry(x[1].toFloat(), weekPetData.avg.toFloat()))
        }

        // 생성된 데이터를 담음
        var set1 = BarDataSet(values1, "DataSet 1")
        var set2 = BarDataSet(values2, "dataSet 2")

        //set1.setCircleColor(ContextCompat.getColor(requireContext(), R.color.green_40C5))
        set1.color = ContextCompat.getColor(requireContext(), R.color.green_40C5)
        set1.valueTextColor = ContextCompat.getColor(requireContext(), android.R.color.transparent)
        //set1.circleHoleColor = ContextCompat.getColor(requireContext(), R.color.green_40C5)

        //set2.setCircleColor(android.R.color.transparent)
        set2.color = ContextCompat.getColor(requireContext(), android.R.color.transparent)
        set2.valueTextColor = ContextCompat.getColor(requireContext(), android.R.color.transparent)
        //set2.circleHoleColor = ContextCompat.getColor(requireContext(), android.R.color.transparent)

        val lineData = BarData(set1)
        lineData.addDataSet(set1)
        lineData.addDataSet(set2)

        lineData.barWidth = 0.35f



        val xAxis = binding.barChart.xAxis
        //Label 표시 여부
        xAxis.setDrawLabels(true)
        //x축 Label이 나타날 위치 설정
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        //x축 최소값 설정
        xAxis.axisMinimum = 0.5f
        //Grid, AxisLine 표시 설정
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)
        xAxis.granularity = 1f

        xAxis.valueFormatter = IndexAxisValueFormatter(day)

        val yAxis: YAxis
        yAxis = binding.barChart.axisLeft
        binding.barChart.axisRight.isEnabled = false
        xAxis.textSize = 10f
        yAxis.textSize = 10f
        yAxis.textColor = Color.BLACK
        yAxis.axisLineColor = android.R.color.transparent

        yAxis.setDrawAxisLine(false)
        yAxis.setDrawGridLines(true)
        yAxis.axisMaximum = 32f
        yAxis.axisMinimum = 0f

        // 차트 하단에 표시되는 description 표시 여부 설정
        binding.barChart.description.isEnabled = false
        // 차트 왼쪽 하단에 표시되는 legent 표시 여부 설정
        binding.barChart.legend.isEnabled = false
        // 차트에 데이터 설정
        binding.barChart.data = lineData
        // 차트 높이값 설정
        //binding.barChart.layoutParams.height = 800
        // 차트 HightLight 표시 여부 설정
        binding.barChart.data.isHighlightEnabled = false
        binding.barChart.animateY(1000)

        binding.barChart.invalidate()
    }

}