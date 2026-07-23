#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
MAVEN_REPO_LOCAL="${MAVEN_REPO_LOCAL:-${SCRIPT_DIR}/.m2/repository}"
has_repo_property=0
for argument in "$@"; do
  [[ "${argument}" == -Dmaven.repo.local=* ]] && has_repo_property=1
done
if [[ "${has_repo_property}" == 0 ]]; then
  set -- "-Dmaven.repo.local=${MAVEN_REPO_LOCAL}" "$@"
fi

if command -v mvn >/dev/null 2>&1; then
  exec mvn "$@"
fi

MAVEN_VERSION="3.9.9"
MAVEN_CACHE_DIR="${MAVEN_CACHE_DIR:-.maven}"
MAVEN_HOME="${MAVEN_CACHE_DIR}/apache-maven-${MAVEN_VERSION}"

if [[ ! -x "${MAVEN_HOME}/bin/mvn" ]]; then
  mkdir -p "${MAVEN_CACHE_DIR}"
  archive_file="$(mktemp)"
  trap 'rm -f "${archive_file}"' EXIT
  curl --fail --silent --show-error --location \
    "https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/${MAVEN_VERSION}/apache-maven-${MAVEN_VERSION}-bin.tar.gz" \
    --output "${archive_file}"
  tar -xzf "${archive_file}" -C "${MAVEN_CACHE_DIR}"
fi

exec "${MAVEN_HOME}/bin/mvn" "$@"
