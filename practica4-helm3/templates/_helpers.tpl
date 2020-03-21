{{/*
Expand the name of the chart.
*/}}

{{- define "practica4.name" -}}
{{- default .Release.Name -}}
{{- end -}}

{{/*
Common labels
*/}}
{{- define "practica4.labels" -}}
{{ include "practica4.selectorLabels" . }}
{{- end -}}

{{/*
Selector labels
*/}}
{{- define "practica4.selectorLabels" -}}
app.kubernetes.io/name: {{ include "practica4.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end -}}