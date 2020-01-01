import axios from 'axios';
import {
  parseHeaderForLinks,
  loadMoreDataWhenScrolled,
  ICrudGetAction,
  ICrudGetAllAction,
  ICrudPutAction,
  ICrudDeleteAction
} from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IProtocolo, defaultValue } from 'app/shared/model/protocolo.model';

export const ACTION_TYPES = {
  FETCH_PROTOCOLO_LIST: 'protocolo/FETCH_PROTOCOLO_LIST',
  FETCH_PROTOCOLO: 'protocolo/FETCH_PROTOCOLO',
  CREATE_PROTOCOLO: 'protocolo/CREATE_PROTOCOLO',
  UPDATE_PROTOCOLO: 'protocolo/UPDATE_PROTOCOLO',
  DELETE_PROTOCOLO: 'protocolo/DELETE_PROTOCOLO',
  RESET: 'protocolo/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IProtocolo>,
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ProtocoloState = Readonly<typeof initialState>;

// Reducer

export default (state: ProtocoloState = initialState, action): ProtocoloState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PROTOCOLO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PROTOCOLO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PROTOCOLO):
    case REQUEST(ACTION_TYPES.UPDATE_PROTOCOLO):
    case REQUEST(ACTION_TYPES.DELETE_PROTOCOLO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PROTOCOLO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PROTOCOLO):
    case FAILURE(ACTION_TYPES.CREATE_PROTOCOLO):
    case FAILURE(ACTION_TYPES.UPDATE_PROTOCOLO):
    case FAILURE(ACTION_TYPES.DELETE_PROTOCOLO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROTOCOLO_LIST): {
      const links = parseHeaderForLinks(action.payload.headers.link);

      return {
        ...state,
        loading: false,
        links,
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links),
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    }
    case SUCCESS(ACTION_TYPES.FETCH_PROTOCOLO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PROTOCOLO):
    case SUCCESS(ACTION_TYPES.UPDATE_PROTOCOLO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PROTOCOLO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/protocolos';

// Actions

export const getEntities: ICrudGetAllAction<IProtocolo> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_PROTOCOLO_LIST,
    payload: axios.get<IProtocolo>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IProtocolo> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PROTOCOLO,
    payload: axios.get<IProtocolo>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IProtocolo> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PROTOCOLO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const updateEntity: ICrudPutAction<IProtocolo> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PROTOCOLO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IProtocolo> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PROTOCOLO,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
