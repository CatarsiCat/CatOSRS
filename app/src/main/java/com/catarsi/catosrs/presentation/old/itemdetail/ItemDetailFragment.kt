package com.catarsi.catosrs.presentation.old.itemdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.catarsi.catosrs.R
import com.catarsi.catosrs.databinding.FragmentItemDetailBinding
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartZoomType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.github.aachartmodel.aainfographics.aaoptionsmodel.AAScrollablePlotArea
import com.github.aachartmodel.aainfographics.aatools.AAColor
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class ItemDetailFragment : Fragment() {

    private lateinit var fragmentBinding: FragmentItemDetailBinding
    private val viewModel: ItemDetailViewModel by viewModels()
    private val args: ItemDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.oldItem = args.oldItem
        viewModel.getTimeseries()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_item_detail, container, false)
        fragmentBinding.itemDetailViewModel = viewModel
        (activity as AppCompatActivity).supportActionBar?.title = viewModel.oldItem.name

        setupChart()

        return fragmentBinding.root
    }

    private fun setupChart() {
        viewModel.timeseries.observe(viewLifecycleOwner, { timeseries ->
            timeseries?.let {
                val avgHighPrices = mutableListOf<Int>()
                val avgLowPrices = mutableListOf<Int>()
                val dates = mutableListOf<String>()
                val format: DateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ROOT)
                format.timeZone = TimeZone.getTimeZone("GMT")
                for (timeserie in it) {
                    avgHighPrices.add(timeserie.avgHighPrice)
                    avgLowPrices.add(timeserie.avgLowPrice)
                    val date = Date(timeserie.timestamp * 1000L)
                    dates.add(format.format(date))
                }

                val aaChartModel: AAChartModel = AAChartModel()
                    .chartType(AAChartType.Line)
                    .title(viewModel.oldItem.id.toString())
                    .subtitle("Wusky is fat")
                    .dataLabelsEnabled(false)
                    .zoomType(AAChartZoomType.X)
                    .scrollablePlotArea(
                        AAScrollablePlotArea()
                            .minWidth(9000)
                            .scrollPositionX(1f)
                    )
                    .categories(dates.toTypedArray())
                    .series(
                        arrayOf(
                            AASeriesElement()
                                .name("HighPrices")
                                .data(avgHighPrices.toTypedArray())
                                .color(AAColor.Orange),
                            AASeriesElement()
                                .name("LowPrices")
                                .data(avgLowPrices.toTypedArray())
                                .color(AAColor.Blue)

                        )
                    )

                fragmentBinding.aaChartView.aa_drawChartWithChartModel(aaChartModel)
            }
        })
    }


}