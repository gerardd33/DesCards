
var units = [
  {unit: "min", max: 60},
  {unit: "h", max: 24},
  {unit: "d", max: 30},
  {unit: "m", max: 12},
  {unit: "y", max: 1000000},
]

export var parseInterval = function (interval) {
    for (var entry of units) {
      if (interval < entry.max) {
        return interval.toString() + ' ' + entry.unit
      }
      interval = Math.floor(interval / entry.max)
    }
}
